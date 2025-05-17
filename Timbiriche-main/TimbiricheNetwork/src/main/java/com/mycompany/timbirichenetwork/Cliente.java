/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.timbirichenetwork;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

/**
 *
 * @author joseq
 */
public class Cliente {

    private final String host;
    private final int puerto;
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private EventoRedListener listener;

    /**
     * Interfaz de callback para quien use el Cliente
     */
    public interface EventoRedListener {

        void onEvento(EventoRed ev);
    }

    /**
     * Constructor.
     *
     * @param host dirección del servidor
     * @param puerto puerto del servidor
     * @param listener listener inicial (puede ser null)
     */
    public Cliente(String host, int puerto, EventoRedListener listener) {
        this.host = host;
        this.puerto = puerto;
        this.listener = listener;
    }

    /**
     * Permite cambiar o asignar el listener tras la creación
     */
    public void setListener(EventoRedListener listener) {
        this.listener = listener;
    }

    /**
     * Conecta al servidor y arranca el hilo lector
     */
    public void connect() throws IOException {
        socket = new Socket(host, puerto);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        new Thread(this::readLoop).start();
    }

    /**
     * Bucle que lee líneas JSON y notifica por callback
     */
    private void readLoop() {
        try {
            String line;
            while ((line = in.readLine()) != null) {
                EventoRed ev = Protocolo.decode(line);
                if (listener != null) {
                    listener.onEvento(ev);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Envía un EventoRed al servidor
     */
    public void send(EventoRed ev) throws IOException {
        String msg = Protocolo.encode(ev);
        out.write(msg);
        out.newLine();
        out.flush();
    }

    /**
     * Cierra la conexión
     */
    public void disconnect() throws IOException {
        socket.close();
    }

}
