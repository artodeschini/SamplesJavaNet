package org.todeschini.samples;

import java.net.*;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Scanner;

public class Main {

    private static final String OPTIONS =
            "select:\n0 - exit\n1 list network interfaces\n2 - url info\n3 - uri info\n4 IntAddress";

    /**
     * this show how to use class NetworkInterface to list all interfaces for network is in a computer
     */
    public static void main(String[] args) {
        System.out.println(OPTIONS);
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        while (i != 0) {
            switch (i) {
                case 1:
                    showNetworkInterfaces();
                    break;
                case 2:
                    showUrlInfos();
                    break;
                case 3:
                    showUriInfo();
                    break;
                case 4:
                    showIntAddress();
                    break;
                default:
                    System.out.println("Invalid option");
            }
            System.out.println(OPTIONS);
            i = scanner.nextInt();
        }
    }

    public static void showNetworkInterfaces() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            System.out.println("Network displays \n");
            for (NetworkInterface i : Collections.list(interfaces)) {
                System.out.printf("%-8s %-32s \n", i.getName(), i.getDisplayName());
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public static void showUrlInfos() {
        try {
            URL url = new URL("https://pt.wikipedia.org/wiki/URI#Refer%C3%AAncias");
            System.out.println("HOST " + url.getHost());
            System.out.println("PATH " + url.getPath());
            System.out.println("REF " + url.getRef());
            System.out.println("PORT" + url.getPort());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void showUriInfo() {
        String str = "https://pt.wikipedia.org/wiki/URI#Refer%C3%AAncias";
        try {
            URI uri = new URI(str);
            System.out.println(uri.getAuthority());
            System.out.println(uri.getPath());
            System.out.println(uri.getHost());
            System.out.println(uri.getPort());
            System.out.println(uri.getFragment());
            System.out.println(uri.getScheme());
            System.out.println(uri.getQuery());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void showIntAddress() {
        try {
            InetAddress[] address = InetAddress.getAllByName("pt.wikipedia.org");
            for (InetAddress a : address) {
                System.out.println(a);
                System.out.println("is local adress? " + (a.isAnyLocalAddress() ? "yes" : "no"));
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
