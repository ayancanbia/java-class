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

		List<Employee> list = new ArrayList<>(); // lista é do tipo INTERFACE e não pode ser instanciada!!

		System.out.print("How many employees will be registered? ");
		int N = sc.nextInt();

		for (int i = 0; i < N; i++) { // for para digitar os dados dos funcionários criando uma lista
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
		int idSalary = sc.nextInt(); // necessário achar o elemento na lista (FUNÇÃO AUXILIAR)

		/**
		 * 2ª opção: Employee emp = list.stream().filter(x -> x.getId() ==
		 * id.Salary).findFirst().orElse(null); list.stream: transforma a lista em um
		 * stream (tipo especial do java que aceita expressoes lambda) função .filter:
		 * filtra na lista somente aqueles que atenderem o predicado entre ().
		 * predicado: "somente os funcionários x, tal que, x.getId seja igual ao
		 * idSalary e pega o primeiro (findFirst)" se não existir, retornar null
		 * (or.Else)
		 */

		Integer pos = position(list, idSalary); // procurando a posição do iDsalary na lista
		if (pos == null) {
			System.out.println("This ID does not exist!");
		} else {
			System.out.print("Enter the percentage: ");
			double percent = sc.nextDouble();
			list.get(pos).increaseSalary(percent); // acessar na lista (get(pos)) o funcionário chamado pela posição
													// 'pos' e chamar a função p aumentar salário
		}

		System.out.println();
		System.out.println("List of employees: ");
		for (Employee emp : list) {
			System.out.println(emp);
		}

		sc.close();
	}
	// função auxiliar para procurar um elemento na lista - o método dirá qual a
	// posição do id na lista

	public static Integer position(List<Employee> list, int id) { // o método recebe uma lista de funcionários e um id
		for (int i = 0; i < list.size(); i++) { // list.size = tamanho da lista. i começa em 0 e caminha enquanto ela
												// for menor queo tamanho da lista
			if (list.get(i).getId() == id) { // testar se o id q eu to procurando é igual o da lista-- se o list.get
												// (get = função que pega o elemento na posição) na posição i +getID
												// (pegou o id), se for igual = encontrou o id na lista. posição = i
				return i; // retornar a posição
			}
		}
		return null; // indicar que o elemento não foi encontrado
	}

	public static boolean hasId(List<Employee> list, int id) { // busca com a expressão lambda -- filtra na lista todo
																// mundo que tem o id igual, pega o primeiro ou volta
																// nulo
		Employee emp = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
		return emp != null; // se o funcionário for diferente de nulo, eu encontrei o funcionário, ou seja,
							// retornará TRUE caso o ID já exista
	}
}
