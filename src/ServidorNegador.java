import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

public class ServidorNegador implements INegador {
	public int niega(int numero) throws RemoteException {
		System.out.println("Se pidio negar" + numero);
		return -numero;
	}

	public static void main(String[] args) throws RemoteException {
		if (args.length != 1) {
			System.out.println("Indique nombre con el que registrar el objeto como argumento");
			return;
		}

		// Exportamos el objeto

		System.out.println("Intentando exportar el objeto...");
		INegador n = (INegador) new ServidorNegador();
		UnicastRemoteObject.exportObject(n);

		// Buscamos el registro para registrar el objeto remoto exportado

		System.out.println("Objeto exportado \n buscando el registro de RMI...");
		Registry r = LocateRegistry.getRegistry(Registry.REGISTRY_PORT);
		r.rebind(args[0], n);
		System.out.println("Objeto registrado \n Esperando conexiones...");

	}// Fin main

}// Fin clase