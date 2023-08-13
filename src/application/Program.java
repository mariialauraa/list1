package application;

import entities.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("How many employees will be registered? ");
        int n = sc.nextInt();
        System.out.println();

        List<Employee> list = new ArrayList<>(); //instancia com uma classe concreta q implementa a interface.

        for (int i = 0; i < n; i++){
            System.out.println("Employee #" + (i+1) + ":");
            System.out.print("Id: ");
            Integer id = sc.nextInt(); //wrapper class
            while (hasId(list, id)) {
                System.out.print("Id already taken! Try again: ");
                id = sc.nextInt();
            }
            System.out.print("Name: ");
            sc.nextLine(); //limpa
            String name = sc.nextLine();
            System.out.print("Salary: ");
            Double salary = sc.nextDouble(); //wrapper class
            System.out.println();

            //instanciar um Employee
            Employee emp = new Employee(id, name, salary);

            //inserir na lista
            list.add(emp);
        }

        System.out.print("Enter the employee id that will have salary increase: ");
        int idSalary = sc.nextInt();

        Integer posi = position(list, idSalary); //procurando a posicao do idSalary dentro da lista.
        if (posi == null){
            System.out.println("This id does not exist!");
        } else {
            System.out.print("Enter the percentage: ");
            double percent = sc.nextDouble();

            //acessar na lista o funcionario da posicao (posi) e chamar a funcao para aumentar o salario.
            list.get(posi).increaseSalary(percent);
        }

        System.out.println();
        System.out.println("List of employees:");
        for (Employee emp : list) { //for each
            System.out.println(emp);
        }

        sc.close();
    }

    //funcao auxiliar para procurar um elemento na lista - posicao do elemento na lista
    public static Integer position(List<Employee> list, int id) { //fora do main, outro list.
        for (int i = 0; i < list.size(); i++){ //percorre a lista.
            if (list.get(i).getId() == id){ //testa se encontra id.
                return i;
            }
        }
        return null;
    }

    //funcao auxiliar para testar se o id já existe na lista
    public static boolean hasId(List<Employee> list, int id) { //employee já existe na lista T or F?
        Employee emp = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        return emp != null;
    }


}
