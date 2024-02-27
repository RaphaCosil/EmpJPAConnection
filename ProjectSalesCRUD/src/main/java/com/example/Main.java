package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        empDAO empregadoDAO = new empDAO();
        Scanner read = new Scanner(System.in);
        try {
            int opcao = 10;
            do {
                System.out.println("O que você deseja fazer?");
                System.out.println("[1]- Insert employee | [2]- See all | [3]- See one | [4]- Update Name ");
                System.out.println(" [5]- Update Salary | [6]- Delete employee | [0]- Exit ");
                opcao = read.nextInt();

                //Insert by ID, name, job, manager ID, hire date,
                //salary amount, commission amount and department ID
                if (opcao == 1) {
                    boolean result;
                    do {
                        result = empVerification.runInsert(empregadoDAO);
                    } while (!result);

                    //Show all users
                } else if (opcao == 2) {
                    boolean result;
                    do {
                        result = empVerification.runReadAll(empregadoDAO);
                    } while (!result);

                    //Find a user by ID
                } else if (opcao == 3) {
                    boolean result;
                    do {
                        result = empVerification.runReadOne(empregadoDAO);
                    } while (!result);

                    //Alter name by ID and the new name
                } else if (opcao == 4) {
                    boolean result;
                    do {
                        result = empVerification.runUpdateName(empregadoDAO);
                    } while (!result);

                    //Alter salary by ID and the new salary
                } else if (opcao == 5) {
                    boolean result;
                    do {
                        result = empVerification.runUpdateSalary(empregadoDAO);
                    } while (!result);

                    //Delete user by an ID
                } else if (opcao == 6) {
                    boolean result;
                    do {
                        result = empVerification.runRemoveUser(empregadoDAO);
                    } while (!result);

                }
            } while (opcao != 0);
        }finally {
            if(empregadoDAO.disconnectEmf()){
                System.out.println("Closed connection to the database");
            }
            else{
                System.out.println("Error closing connection to the database");

            }

        }

    }
}