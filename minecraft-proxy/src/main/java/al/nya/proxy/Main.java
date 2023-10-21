package al.nya.proxy;

import al.nya.proxy.network.ProxyServer;
import al.nya.proxy.utils.DataUtils;
import al.nya.proxy.utils.PacketBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.apache.commons.cli.*;
import org.fusesource.jansi.AnsiConsole;

import java.util.Arrays;

public class Main {
    private static String remoteIP = "";
    private static int remotePort = 0;
    private static int localPort = 0;
    private static ProxyServer proxyServer = null;

    public static void main(String[] args) {
        args = new String[]{"-r", "203.135.96.39", "-p", "25565"};
        AnsiConsole.systemInstall();
        Runtime.getRuntime().addShutdownHook(new Thread(Main::shutdown));
        Logger.info("Starting...");
        Options options = new Options();
        options.addOption("r", "remote", true, "Remote address");
        options.addOption("p", "port", true, "Local port");
        options.addOption("d", "debug", false, "Debug mode");
        CommandLine commandLine = null;
        CommandLineParser parser = new PosixParser();
        HelpFormatter hf = new HelpFormatter();
        hf.setWidth(110);
        try {
            commandLine = parser.parse(options, args);
            if (commandLine.hasOption("remote")) {
                processAddress(commandLine.getOptionValue("remote"));
            } else {
                throw new ParseException("No remote address specified");
            }
            if (commandLine.hasOption("port")) {
                localPort = Integer.parseInt(commandLine.getOptionValue("port"));
                Logger.info("Local port: " + localPort);
            } else {
                throw new ParseException("No local port specified");
            }
            if (commandLine.hasOption("debug")) {
                Logger.debug = true;
            }
        } catch (ParseException e) {
            Logger.error("Parse error: " + e.getMessage());
            hf.printHelp("Proxy", options, true);
            return;
        }
        startServer();
    }

    private static void startServer() {
        proxyServer = new ProxyServer(remoteIP, remotePort, localPort);
        proxyServer.start();
    }

    private static void processAddress(String address) {
        String[] split = address.split(":");
        if (split.length == 2) {
            remoteIP = split[0];
            remotePort = Integer.parseInt(split[1]);
        } else if (split.length == 1) {
            remoteIP = split[0];
            remotePort = 25565;
        } else {
            Logger.error("Invalid address");
            System.exit(1);
        }
        Logger.info("Remote address: " + remoteIP + ":" + remotePort);
    }

    private static void shutdown() {
        System.out.println("Shutting down...");
        if (AnsiConsole.isInstalled()) {
            AnsiConsole.systemUninstall();
            System.out.println("AnsiConsole uninstalled.");
        }
    }
}
