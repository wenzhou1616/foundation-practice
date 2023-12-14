package com.herry.code.dome;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author herry
 */
public class UDPSend {
    public static void main(String[] args) {
        try (DatagramSocket ds = new DatagramSocket()) {
            byte[] by = "hello".getBytes();
            DatagramPacket dp = new DatagramPacket(by, 0, by.length, InetAddress.getByName("127.0.0.1"), 10000);
            ds.send(dp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
