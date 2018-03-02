import java.net.*;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

public class ClienteNegador {
	public static void main(String[] args) throws RemoteException {
		// Como parametros nos pasan la URL del objeto
		// y el numero a negar
		if (args.length != 2) {
			System.out.println("Indique como argumentos la URL del objeto y el numero a negar");
			return;
		}
		// Obtenemos el numero de los argumentos

		int numero;
		try {
			numero = Integer.parseInt(args[1]);
		} catch (NumberFormatException ex) {
			System.out.println("El numero " + args[1] + "no es válido");
			return;
		}

		// Buscamos el objeto

		System.out.println("Buscando el objeto...");
		INegador negador;
		try {
			negador = (INegador) Naming.lookup(args[0]);
		} catch (NotBoundException ex) {
			System.out.println("El objeto " + args[0] + "no existe en el servidor");
			return;
		} catch (MalformedURLException ex) {
			System.out.println("URL mal escrita: " + args[0]);
			return;
		}
		System.out.println("Objeto encontrado...");
		System.out.println("El negado de " + numero + "es" + negador.niega(numero));

	}// fin main
}// Fin clase
