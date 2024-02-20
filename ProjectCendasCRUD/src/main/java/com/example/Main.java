package com.example;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        empDAO empregadoDAO= new empDAO();
        Scanner read=new Scanner(System.in);
        int opcao=10;

        do{
                System.out.println("O que você deseja fazer?");
                System.out.println("[1]- Criar empregado | [2]- Ver todos | [3]- Ver um | [4]- Atualizar Nome ");
                System.out.println(" [5]- Atualizar salário | [6]- Remover usuário | [0]- Sair ");
                opcao = read.nextInt();

        //Inserir, tem como parâmetro o ID, o nome, o cargo, o ID do gerente, a data de contraração
        //o valor do salário, o valor da comissão e o ID do departamento
                if (opcao == 1) {
                    try {
                        int newId;
                        System.out.println("Digite o ID do empregado: ");
                        newId = read.nextInt();
                        //                    empregadoDAO.insert(newId, "Name" + newId, "MockJob", 7902, new Date(2023, 07-1, 07), 2000.0, 200.0, 10);

                        System.out.println("Digite o nome do empregado: ");
                        String newName = read.next();
                        System.out.println("Digite o cargo do empregado: ");
                        String newJob = read.next();
                        System.out.println("Digite o id do gerente: ");
                        int newMgr = read.nextInt();
                        System.out.println("Digite o ano da contratação : ");
                        int newYear = read.nextInt();
                        System.out.println("Digite o mês da contratação: ");
                        int newMonth = read.nextInt();
                        System.out.println("Digite o dia da contratação: ");
                        int newDay = read.nextInt();
                        System.out.println("Digite o valor do salário: ");
                        Double newSal = read.nextDouble();
                        System.out.println("Digite o valor da comissão: ");
                        Double newCom = read.nextDouble();
                        System.out.println("Digite o id do departamento: ");
                        int newDeptId = read.nextInt();
                        empregadoDAO.insert(newId, newName, newJob, newMgr, new Date(newYear-1900, newMonth - 1, newDay), newSal, newCom, newDeptId);
                    } catch (InputMismatchException errorMismatch) {
                        System.out.println("ERROR typo, try again -- " + errorMismatch);
                    }

        //Ver todos, não tem parâmetro
                } else if (opcao == 2) {

                    List<empData> empList = empregadoDAO.getAll();
                    for (Object o : empList) {
                        System.out.println(o.toString());
                    }

        //Ver um usuário, tem como parâmetro o ID

                } else if (opcao == 3) {
                    try {
                        System.out.println("Digite o ID do empregado: ");
                        int id = read.nextInt();
                        empregadoDAO.getOne(id);
                    } catch (NullPointerException error) {
                        System.out.println("Esse usuário não existe");
                    } catch (InputMismatchException errorMismatch) {
                        System.out.println("ERROR typo, try again -- " + errorMismatch);
                    }


        //Alterar nome, tem como parâmetro o ID e o nome
                } else if (opcao == 4) {
                    try {
                        boolean verifica;
                        do {
                        System.out.println("Digite o ID do empregado: ");
                        int id = read.nextInt();
                        verifica=VerifyID(id);
                            if(verifica){
                                System.out.println("Digite o novo nome: ");
                                String nome = read.next();
                                empregadoDAO.updateName(id, nome);

                            }
                            else{
                                System.out.println("Type an ID with four digits [1000-9999]");

                            }
                        }while(!verifica);

                    } catch (InputMismatchException errorMismatch) {
                            System.out.println("ERROR typo, try again -- " + errorMismatch);
                        }
                    catch (NullPointerException errorNull){
                        System.out.println("Não existe nenhum usuário com esse ID");
                    }

        //Alterar salario, tem como parâmetro ID e salário
                } else if (opcao == 5) {
                    try {
                        boolean verifica;
                        do {
                            System.out.println("Digite o ID do empregado: ");
                            int id = read.nextInt();
                            verifica=VerifyID(id);
                            if(verifica){
                                System.out.println("Digite o novo salario: ");
                                double salario = read.nextDouble();
                                empregadoDAO.updateSalary(id, salario);
                            }
                            else{
                                System.out.println("Digite um id com quatro digitos [1000-9999]");
                            }
                        }while(!verifica);
                    } catch (InputMismatchException errorMismatch) {
                        System.out.println("ERROR typo, try again -- " + errorMismatch);
                    }
                    catch (NullPointerException errorNull){
                        System.out.println("Não existe nenhum usuário com esse ID");
                    }
        //Remover, tem como parâmetro o ID
                } else if (opcao == 6) {
                    try {
                        boolean verifica;
                        do {
                            System.out.println("Digite o ID do empregado: ");
                            int id = read.nextInt();
                            verifica=VerifyID(id);
                            if (verifica) {
                                empregadoDAO.deleteUser(id);
                            }
                        }while(!verifica);
                    }  catch (InputMismatchException errorMismatch) {
                        System.out.println("ERROR typo, try again -- " + errorMismatch);
                    }
                    catch (NullPointerException errorNull){
                        System.out.println("Não existe nenhum usuário com esse ID");
                    }

                }



        }while (opcao!=0);

        empregadoDAO.disconnect();


    }
    //Função de verificação do ID
    public static boolean VerifyID(int id){
        if(id>=1000&& id<=9999){
            return true;
        }
        else{
            return false;
        }
    }

}
