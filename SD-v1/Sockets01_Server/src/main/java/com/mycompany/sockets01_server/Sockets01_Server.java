/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sockets01_server;

/**
 *
 * @author joao
 */
public class Sockets01_Server {

    public static void main(String[] args) {
        ServidorTCP servidor = new ServidorTCP();
        servidor.execute();
    }
}
