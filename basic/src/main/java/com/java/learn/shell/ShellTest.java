package com.java.learn.shell;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ShellTest {
    private static long MAX_WAIT_TIME = 60 * 1000L;

    public static void send(String msg) throws Exception {
        String cmd = "/bin/bash /root/project/java/smsalarm.sh " + msg;
        Process process = Runtime.getRuntime().exec(cmd);
        long waitTime = 0;
        int exitCode = -1;
        while (waitTime < MAX_WAIT_TIME) {
            try {
                exitCode = process.exitValue();
                break;
            } catch (IllegalThreadStateException e) {
                // nothing, just wait
                Thread.sleep(500);
                waitTime += 500;
            }
        }

        StringBuilder sb = new StringBuilder();
        InputStream is = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        process.waitFor();
        is.close();
        String returnMsg = sb.toString();
        System.out.println(msg);
        System.out.println(returnMsg);

        if (waitTime >= MAX_WAIT_TIME) {
            System.out.println("exec process waiting time out");
            process.destroy();
            return;
        }

        if (exitCode != 0) {
            System.out.println("exec process exit code:" + exitCode);
        }
    }

    public static void main(String[] args) {
        try {
            send("test1\\ntest2");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}