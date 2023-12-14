package com.herry.code.dome;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author herry
 */
public class UDPReceive {
    public static void main(String[] args) {
        try (DatagramSocket ds = new DatagramSocket(10000)) {
            byte[] by = new byte[1024 * 64];
            DatagramPacket dp = new DatagramPacket(by, by.length);
            ds.receive(dp);
            String str = new String(dp.getData(), 0, dp.getLength());
            System.out.println(str + "--" + dp.getAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
