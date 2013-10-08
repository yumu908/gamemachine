module GameMachine
  module Endpoints
    class TcpHandler < Actor::Base
      GAME_HANDLER = Settings.game_handler

      def post_init(*args)
        @con_ref = nil
        @client_id = nil
      end

      def on_receive(message)
        if message.kind_of?(JavaLib::Tcp::ConnectionClosed)
          client_message = client_disconnect_message(@client_id)
          Actor::Base.find(GAME_HANDLER).tell(client_message,get_self)
          get_context.stop(get_self)
        elsif message.kind_of?(JavaLib::Tcp::Received)
          handle_incoming(message)
        elsif message.is_a?(ClientMessage)
          handle_outgoing(message)
        elsif message.kind_of?(JavaLib::Tcp::Connected)
          @con_ref = get_sender
          @client_id = message.remote_address.to_s
        else
          unhandled(message)
        end
      end

      private

      def client_disconnect_message(client_id)
        ClientMessage.new.set_client_disconnect(
          ClientDisconnect.new.set_client_connection(
            ClientConnection.new.set_id(client_id).set_gateway(self.class.name)
          )
        )
      end

      def handle_outgoing(message)
        byte_string = JavaLib::ByteString.from_array(message.to_byte_array)
        tcp_message = JavaLib::TcpMessage.write(byte_string)
        @con_ref.tell(tcp_message, get_self)
      rescue Exception => e
        GameMachine.logger.error "#{self.class.name} #{e.to_s}"
      end

      def handle_incoming(message)
        client_message = create_client_message(
          message.data.to_array,@client_id
        )
        Actor::Base.find(GAME_HANDLER).tell(client_message,get_self)
      rescue Exception => e
        GameMachine.logger.error "#{self.class.name} #{e.to_s}"
      end

      def create_client_message(data,client_id)
        ClientMessage.parse_from(data).set_client_connection(
          ClientConnection.new.set_id(client_id).set_gateway(self.class.name).
          set_server(Application.config.name)
        )
      end

    end
  end
end