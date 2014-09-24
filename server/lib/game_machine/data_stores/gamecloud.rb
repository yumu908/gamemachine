module GameMachine
  module DataStores
    class Gamecloud

      attr_reader :serialization
      def initialize(serialization)
        @serialization = serialization
      end

      def connect
        JavaLib::CloudClient.get_instance.set_credentials(
          Application.config.gamecloud.host,
          Application.config.gamecloud.user,
          Application.config.gamecloud.api_key
        )
      end

      def handle_entity_response(response)
        if response.status == 200
          serialization == 'json' ? response.stringBody : response.byteBody
        elsif response.status == 404
          nil
        else
          raise "Gamecloud.get returned status: #{response.status}"
        end
      end

      def handle_response(response)
        if [200,204].include?(response.status)
          true
        elsif response.status == 404
          false
        else
          raise "Gamecloud.get returned status: #{response.status}"
        end
      end

      def query(query,limit)
        response = JavaLib::CloudClient.get_instance.query(query,limit,serialization)
        if response.status == 200
          response.byteBody
        elsif response.status == 404
          nil
        else
          raise "Gamecloud.get returned status: #{response.status}"
        end
      end

      def get(id)
        if serialization == 'json'
          response = JavaLib::CloudClient.get_instance.getString(id)
        else
          response = JavaLib::CloudClient.get_instance.getBytes(id)
        end
        handle_entity_response(response)
      end

      def delete(id)
        response = JavaLib::CloudClient.get_instance.delete(id)
        handle_response(response)
      end

      def delete_matching(query_string)
        response = JavaLib::CloudClient.get_instance.delete_matching(query_string)
        handle_response(response)
      end

      def set(id,value)
        if serialization == 'json'
          response = JavaLib::CloudClient.get_instance.putString(id,value)
        else
          response = JavaLib::CloudClient.get_instance.putBytes(id,value)
        end
        handle_response(response)
      end
    end
  end
end