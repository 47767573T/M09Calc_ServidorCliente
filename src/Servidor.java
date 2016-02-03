import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by 47767573t on 03/02/16.
 */
public class Servidor {



    public static void main(String[] args) {
        System.out.println("...creando servidor");

        /*
        El constructor del serverSocket es diferente del cliente
        Tiene metodos que el cliente no tiene
         */

        try {
            String msgSalida;
            String msgEntrada;


            ServerSocket serverSocket = new ServerSocket();
            System.out.println("...realizando bind");//bind = vincular
            InetSocketAddress addr = new InetSocketAddress("0.0.0.0", 5555);

            serverSocket.bind(addr);//el servidor escucha en la direccion que le digamos

            /*
            El servidor tiene que ser la misma maquina que ejecuta
            el programa servidor???
             */

            System.out.println("...escuchando");
            /*
            Aqui es donde el servidor se quedara escuchando asta que reciba una conexion
             */
            Socket socketDeEscucha = serverSocket.accept();
            System.out.println("...se ha recibido una llamada");

            InputStream is = socketDeEscucha.getInputStream();
            OutputStream os = socketDeEscucha.getOutputStream();

            msgSalida = "Ha comenzado la calculadora, escriba la operacion";
            os.write(msgSalida.getBytes());

            byte[] mensaje = new byte[500];
            is.read(mensaje);

            msgEntrada = new String(mensaje);
            System.out.println(new String (mensaje));


            if (msgEntrada.contains("+")){

                int posPrimera = msgEntrada.indexOf("+");
                String operando1 = msgEntrada.substring(0,posPrimera);
                String operando2 = msgEntrada.substring(posPrimera+1,msgEntrada.length());

                double op1 = Double.parseDouble(operando1);
                double op2 = Double.parseDouble(operando2);
                double total = op1+op2;

                String msgTotal = op1+" + "+op2+" = "+total;

                System.out.println(msgTotal);
                //OutputStream os1 = socketDeEscucha.getOutputStream();
                //os1.write(msgTotal.getBytes());
                os.write(msgTotal.getBytes());

            }else if (msgEntrada.contains("-")) {
                int posPrimera = msgEntrada.indexOf("-");
                String operando1 = msgEntrada.substring(0, posPrimera);
                String operando2 = msgEntrada.substring(posPrimera + 1, msgEntrada.length());

                double op1 = Double.parseDouble(operando1);
                double op2 = Double.parseDouble(operando2);
                double total = op1 - op2;

                msgSalida = op1 + " - " + op2 + " = " + total;
                System.out.println(msgSalida);

                os.write(msgSalida.getBytes());


            }else if (msgEntrada.contains("*")) {
                int posPrimera = msgEntrada.indexOf("*");
                String operando1 = msgEntrada.substring(0, posPrimera);
                String operando2 = msgEntrada.substring(posPrimera + 1, msgEntrada.length());

                double op1 = Double.parseDouble(operando1);
                double op2 = Double.parseDouble(operando2);
                double total = op1 * op2;

                msgSalida = op1 + " * " + op2 + " = " + total;
                System.out.println(msgSalida);

                os.write(msgSalida.getBytes());

            }else if (msgEntrada.contains("/")) {

                int posPrimera = msgEntrada.indexOf("/");
                String operando1 = msgEntrada.substring(0, posPrimera);
                String operando2 = msgEntrada.substring(posPrimera + 1, msgEntrada.length());

                double op1 = Double.parseDouble(operando1);
                double op2 = Double.parseDouble(operando2);
                double total = op1 / op2;

                msgSalida = op1 + " / " + op2 + " = " + total;
                System.out.println(msgSalida);

                os.write(msgSalida.getBytes());

            }else{
                msgSalida = "QUE COÑO DICES?";
                os.write(msgSalida.getBytes());
            }

            System.out.println(",,,cerrando");

            socketDeEscucha.close();
            serverSocket.close();
            is.close();
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}