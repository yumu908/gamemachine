package com.game_machine.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.MessageBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundMessageHandlerAdapter;
import io.netty.channel.socket.DatagramPacket;

import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.game_machine.messages.ProtobufMessages.ClientMessage;
import com.google.protobuf.ByteString;

public class UdpClientHandler extends ChannelInboundMessageHandlerAdapter<DatagramPacket> {

	private static final Logger log = Logger.getLogger(UdpClientHandler.class.getName());

	
	private ChannelHandlerContext ctx = null;
	private UdpClient client;
	private int messageCount = 0;

	public UdpClientHandler(UdpClient client) {
		this.client = client;
		log.setLevel(UdpClient.logLevel);
	}

	public Boolean sendMessage(String str) {
		if (ctx == null) {
			return false;
		} else {
			ClientMessage.Builder builder = ClientMessage.newBuilder();
			ByteString reply = ByteString.copyFromUtf8(str);
			builder.setBody(reply);
			ClientMessage msg = builder.build();
			ByteBuf bmsg = Unpooled.copiedBuffer(msg.toByteArray());
			DatagramPacket packet = new DatagramPacket(bmsg, new InetSocketAddress(client.host, client.port));
			final MessageBuf<Object> out = ctx.nextOutboundMessageBuffer();
			out.add(packet);
			//ctx.write(packet);
			//log.info("sendMessage " + reply.size() + "  " + msg.getBody().toStringUtf8());
			return true;
		}
	}

	@Override
	public void channelActive(final ChannelHandlerContext ctx) {
		log.warning("UdpClient ECHO active ");
		this.ctx = ctx;
		for (int i = 0; i < 10; i++) {
			sendMessage("GO");
		}
		sendMessage("QUIT");
		ctx.flush();
		//stop();
	}

	public void messageReceived(final ChannelHandlerContext ctx, final DatagramPacket m) {

		messageCount++;
		//log.info("UdpClient messageReceived " + messageCount);
		byte[] bytes = new byte[m.data().readableBytes()];
		m.data().readBytes(bytes);
		ByteString b = ByteString.copyFrom(bytes);
		ClientMessage.Builder builder = ClientMessage.newBuilder();
		builder.setBody(b);
		ClientMessage msg = builder.build();

		if (messageCount >= 10) {
			log.warning("Client received all messages back, stopping");
			stop();
		}
	}

	@Override
	public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) {
		log.log(Level.WARNING, "close the connection when an exception is raised", cause);
		ctx.close();
	}

	public void stop() {
		this.ctx.flush().addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				client.stop();
			}
		});
	}


}
