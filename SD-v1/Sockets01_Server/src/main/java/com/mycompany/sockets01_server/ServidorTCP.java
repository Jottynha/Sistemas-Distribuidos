package com.mycompany.sockets01_server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

public class ServidorTCP {
    public void execute() {
        try {
            ServerSocket server = new ServerSocket(3322);
            JOptionPane.showMessageDialog(null,"Servidor iniciado!\nAguardando Cliente 1...");
            // ==============================
            // CLIENTE 1
            // ==============================
            Socket client1 = server.accept();
            JOptionPane.showMessageDialog(null,"Cliente 1 conectado!");
            ObjectOutputStream writer1 = new ObjectOutputStream(client1.getOutputStream());
            writer1.flush();
            ObjectInputStream reader1 =new ObjectInputStream(client1.getInputStream());
            String msg1 = JOptionPane.showInputDialog(null,"Mensagem para Cliente 1:");
            writer1.writeUTF(msg1);
            writer1.flush();
            JOptionPane.showMessageDialog(null,"Mensagem enviada!\nAguardando resposta...");
            String resposta1 = reader1.readUTF();
            JOptionPane.showMessageDialog(null,"Resposta do Cliente 1:\n" + resposta1);
            reader1.close();
            writer1.close();
            client1.close();
            // ==============================
            // CLIENTE 2
            // ==============================
            JOptionPane.showMessageDialog(null,"Aguardando Cliente 2...");
            Socket client2 = server.accept();
            JOptionPane.showMessageDialog(null,"Cliente 2 conectado!");
            ObjectOutputStream writer2 =new ObjectOutputStream(client2.getOutputStream());
            writer2.flush();
            ObjectInputStream reader2 =new ObjectInputStream(client2.getInputStream());
            String msg2 = JOptionPane.showInputDialog(null,"Mensagem para Cliente 2:");
            writer2.writeUTF(msg2);
            writer2.flush();
            JOptionPane.showMessageDialog(null,"Mensagem enviada!\nAguardando resposta...");
            String resposta2 = reader2.readUTF();
            JOptionPane.showMessageDialog(null,"Resposta do Cliente 2:\n" + resposta2);
            reader2.close();
            writer2.close();
            client2.close();
            server.close();
            JOptionPane.showMessageDialog(null,"[Mensagens totais]\nMensagem 1:'" + msg1 + "'\nResposta1:'" + resposta1
                    + "'\nMensagem 2:'" + msg2 + "'\nResposta2:'" + resposta2);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null,"Erro no servidor:\n" + error);
        }
    }
}