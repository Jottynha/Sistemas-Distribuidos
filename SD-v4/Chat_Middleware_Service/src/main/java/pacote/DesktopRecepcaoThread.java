package pacote;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

public class DesktopRecepcaoThread implements Runnable {
    private boolean paradaManual = false;
    private Socket cliente;
    private ServerSocket receptor;
    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try{
            while(!paradaManual){
                receptor = new ServerSocket(Util.PortaRecepcaoDesktop);
                cliente = receptor.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                String msg = reader.readLine();
                reader.close();
                cliente.close();
                receptor.close();
                JOptionPane.showMessageDialog(null,"Mensagem:" + msg);
                FileWriter fwriter = new FileWriter(Util.PathRepDesktop,true);
                fwriter.write(msg);
                fwriter.close();
            }
        } catch (Exception e){
            if(!paradaManual){
                JOptionPane.showMessageDialog(null,"Erro em DesktopRecepcaoThread::run:\n"+e.getMessage());                
            }
        }
    }
    public void fecharServidor(){
        paradaManual = true;
        try{
            if((cliente != null)&&(!cliente.isClosed())){
                cliente.close();
            } 
            if((receptor != null)&&(!receptor.isClosed())){
                receptor.close();
            }
        } catch (Exception e){
            if(paradaManual){
                JOptionPane.showMessageDialog(null,"Erro em DesktopRecepcaoThread::fecharServidor:\n"+ e.getMessage());
            }         
        }
        
    }
    
    
}
