package pacote;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

public class DesktopEnvioThread implements Runnable{
    private ServerSocket emissor;
    private Socket cliente;
    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            while(true){
                FileReader freader = new FileReader(Util.PathRepDesktop);
                BufferedReader buffer = new BufferedReader(freader);
                String msgs = "";
                while(buffer.ready()){
                    msgs += buffer.readLine();
                }    
                buffer.close();
                freader.close();
                emissor = new ServerSocket(Util.PortaEnvioDesktop);
                emissor.setReuseAddress(true);
                cliente = emissor.accept();
                ObjectOutputStream output = new ObjectOutputStream(cliente.getOutputStream());
                output.writeUTF(msgs);
                output.close();
                cliente.close();
                emissor.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erro em Desktop Envio Thread:" + e.getMessage());
            e.printStackTrace();
            
        }
    }
    
}
