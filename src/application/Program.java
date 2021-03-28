package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Employee> list = new ArrayList<>(); // lista � do tipo INTERFACE e n�o pode ser instanciada!!

		System.out.print("How many employees will be registered? ");
		int N = sc.nextInt();

		for (int i = 0; i < N; i++) { // for para digitar os dados dos funcion�rios criando uma lista
			System.out.println();
			System.out.println("Employee #" + (i + 1) + ":"); // i+1 porque o i foi iniciado com 0
			System.out.print("ID: ");
			Integer id = sc.nextInt();
			while (hasId(list, id)) { // enquanto encontrar o id na lista, repetir
				System.out.print("Id already taken! Try again: ");
				id = sc.nextInt();
			}

			System.out.print("Name: ");
			sc.nextLine(); // limpeza de buffer
			String name = sc.nextLine();
			System.out.print("Salary: ");
			Double salary = sc.nextDouble();

			Employee emp = new Employee(id, name, salary); // instaciando um obj employee com esses dados

			list.add(emp); // adicionando o obj employee instanciado na lista

		}

		System.out.println();
		System.out.print("Enter the employee ID that will have salary increase: ");
		int idSalary = sc.nextInt(); // necess�rio achar o elemento na lista (FUN��O AUXILIAR)

		/**
		 * 2� op��o: Employee emp = list.stream().filter(x -> x.getId() ==
		 * id.Salary).findFirst().orElse(null); list.stream: transforma a lista em um
		 * stream (tipo especial do java que aceita expressoes lambda) fun��o .filter:
		 * filtra na lista somente aqueles que atenderem o predicado entre ().
		 * predicado: "somente os funcion�rios x, tal que, x.getId seja igual ao
		 * idSalary e pega o primeiro (findFirst)" se n�o existir, retornar null
		 * (or.Else)
		 */

		Integer pos = position(list, idSalary); // procurando a posi��o do iDsalary na lista
		if (pos == null) {
			System.out.println("This ID does not exist!");
		} else {
			System.out.print("Enter the percentage: ");
			double percent = sc.nextDouble();
			list.get(pos).increaseSalary(percent); // acessar na lista (get(pos)) o funcion�rio chamado pela posi��o
													// 'pos' e chamar a fun��o p aumentar sal�rio
		}

		System.out.println();
		System.out.println("List of employees: ");
		for (Employee emp : list) {
			System.out.println(emp);
		}

		sc.close();
	}
	// fun��o auxiliar para procurar um elemento na lista - o m�todo dir� qual a
	// posi��o do id na lista

	public static Integer position(List<Employee> list, int id) { // o m�todo recebe uma lista de funcion�rios e um id
		for (int i = 0; i < list.size(); i++) { // list.size = tamanho da lista. i come�a em 0 e caminha enquanto ela
												// for menor queo tamanho da lista
			if (list.get(i).getId() == id) { // testar se o id q eu to procurando � igual o da lista-- se o list.get
												// (get = fun��o que pega o elemento na posi��o) na posi��o i +getID
												// (pegou o id), se for igual = encontrou o id na lista. posi��o = i
				return i; // retornar a posi��o
			}
		}
		return null; // indicar que o elemento n�o foi encontrado
	}

	public static boolean hasId(List<Employee> list, int id) { // busca com a express�o lambda -- filtra na lista todo
																// mundo que tem o id igual, pega o primeiro ou volta
																// nulo
		Employee emp = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
		return emp != null; // se o funcion�rio for diferente de nulo, eu encontrei o funcion�rio, ou seja,
							// retornar� TRUE caso o ID j� exista
	}
}
