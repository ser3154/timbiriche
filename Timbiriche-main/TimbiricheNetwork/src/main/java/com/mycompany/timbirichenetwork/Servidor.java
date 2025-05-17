/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.timbirichenetwork;

import java.net.*;
import java.io.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author joseq
 */
public class Servidor {

    private final int puerto;
    private ServerSocket serverSocket;
    private final List<ClientHandler> clients = new CopyOnWriteArrayList<>();

    public Servidor(int puerto) {
        this.puerto = puerto;
    }

    /**
     * Arranca el servidor en un hilo separado
     */
    public void start() throws IOException {
        serverSocket = new ServerSocket(puerto);
        System.out.println("Servidor escuchando en puerto " + puerto);
        new Thread(() -> {
            try {
                while (!serverSocket.isClosed()) {
                    Socket client = serverSocket.accept();
                    ClientHandler h = new ClientHandler(client);
                    clients.add(h);
                    new Thread(h).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Cierra el servidor y todas las conexiones
     */
    public void stop() throws IOException {
        for (ClientHandler h : clients) {
            h.stop();
        }
        serverSocket.close();
    }

    /**
     * Reenvía un evento a todos los clientes conectados
     */
    private void broadcast(EventoRed ev) {
        String msg = Protocolo.encode(ev);
        for (ClientHandler h : clients) {
            h.send(msg);
        }
    }

    /**
     * Handler de cada cliente
     */
    private class ClientHandler implements Runnable {

        private final Socket socket;
        private final BufferedReader in;
        private final BufferedWriter out;

        ClientHandler(Socket s) throws IOException {
            this.socket = s;
            this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            this.out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        }

        @Override
        public void run() {
            try {
                String line;
                while ((line = in.readLine()) != null) {
                    EventoRed ev = Protocolo.decode(line);
                    // TODO: lógica especial en JOIN/START si quieres
                    broadcast(ev);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                clients.remove(this);
                try {
                    socket.close();
                } catch (IOException ignored) {
                }
            }
        }

        void send(String msg) {
            try {
                out.write(msg);
                out.newLine();
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        void stop() throws IOException {
            socket.close();
        }
    }

}
