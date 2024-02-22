package com.example;

import java.time.LocalDate;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        empDAO empregadoDAO= new empDAO();
        Scanner read=new Scanner(System.in);
        int opcao=10;
            do {
                System.out.println("O que você deseja fazer?");
                System.out.println("[1]- Criar empregado | [2]- Ver todos | [3]- Ver um | [4]- Atualizar Nome ");
                System.out.println(" [5]- Atualizar salário | [6]- Remover usuário | [0]- Sair ");
                opcao = read.nextInt();

                //Insert by ID, name, job, manager ID, hire date,
                //salary amount, commission amount and department ID
                if (opcao == 1) {
                    boolean result;
                    do {
                        result = runInsert(empregadoDAO);
                    } while (result);

                    //Show all users
                } else if (opcao == 2) {
                    boolean result;
                    do {
                        result = runReadAll(empregadoDAO);
                    } while (result);

                    //Find a user by ID
                } else if (opcao == 3) {
                    boolean result;
                    do {
                        result = runReadOne(empregadoDAO);
                    } while (result);

                    //Alter name by ID and the new name
                } else if (opcao == 4) {
                    boolean result;
                    do {
                        result = runUpdateName(empregadoDAO);
                    } while (result);

                    //Alter salary by ID and the new salary
                } else if (opcao == 5) {
                    boolean result;
                    do {
                        result = runUpdateSalary(empregadoDAO);
                    } while (result);

                    //Delete an user by an ID
                } else if (opcao == 6) {
                    boolean result;
                    do {
                        result = runDelete(empregadoDAO);
                    } while (result);

                }
            } while (opcao != 0);

        empregadoDAO.disconnect();

    }
    //Method verifyID, verify if ID has the value between 1000 and 9999
    public static boolean VerifyID(int id){
        if(id>=1000&& id<=9999){
            return true;
        }
        else{
            return false;
        }
    }

    //Methods for all methods from empDAO class, with validations related to the database

    //Runs method insertOne one with validations

    public static boolean runInsert(empDAO empregadoDAO
){
        try {
            Scanner read=new Scanner(System.in);
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

            Date newDate =new Date(newYear-1900, newMonth - 1, newDay);
            String errorMessage="";
            boolean verificacao=true;
            if(newId>9999||newId<1000){
                errorMessage+= " Valor inválido de ID, coloque números entre 1000-9999. ";
                verificacao=false;
            }
            if(newName.length()<=10){
                errorMessage+= " O nome só pode ter 10 caracteres.";
                verificacao= false;
            }
            if(newJob.length()<=10){
                errorMessage+= " O trabalho só pode ter 9 caracteres.";
                verificacao= false;
            }
            if(newMgr>9999||newMgr<1000){
                errorMessage+= " Valor inválido de ID, coloque números entre 1000-9999.";
                verificacao= false;
            }
            if(newSal<=9999999.99){
                errorMessage+= " O salário só pode chegar no limite de 9999999.99.";
                verificacao=false;

            }
            if(newCom<=9999999.99){
                errorMessage+= " A comissão só pode chegar no limite de 9999999.99.";
                verificacao=false;
            }
            if(newDeptId>99||newDeptId<10){
                errorMessage+= " O ID do departamento só pode ter o ID entre 99-10";
                verificacao=false;
            }
            Date comparDate= new Date(LocalDate.now().getYear()-1900, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
            if(newDate.before(comparDate)|| newDate.equals(comparDate) ){
                errorMessage+= " A data deve ser anterior ou igual ao dia de hoje.";
                verificacao=false;

            }
            if(!verificacao){
                System.out.println(errorMessage);
                return verificacao;
            }

            empregadoDAO.insert(newId, newName, newJob, newMgr, newDate , newSal, newCom, newDeptId);
            return true;
        } catch (InputMismatchException errorMismatch) {
            System.out.println("ERROR typo, try again -- " + errorMismatch);
            return false;
        }
    }

    //Runs method readOne with validations
    public static boolean runReadOne(empDAO empregadoDAO){
        try {
            Scanner read=new Scanner(System.in);

            System.out.println("Digite o ID do empregado: ");
            int id = read.nextInt();
            empregadoDAO.getOne(id);
            return true;
        } catch (NullPointerException error) {
            System.out.println("Esse usuário não existe");
            return false;
        } catch (InputMismatchException errorMismatch) {
            System.out.println("ERROR typo, try again -- " + errorMismatch);
            return false;
        }


    }
    //Runs method readAll with validations
    public static boolean runReadAll(empDAO empregadoDAO){
        try {
            List<empData> empList = empregadoDAO.getAll();
            for (Object o : empList) {
                System.out.println(o.toString());
            }
            return true;
        }catch (Exception error){

            System.out.println("ERROR -- " + error);

        return false;
        }
    }

    //Runs method updateName with validations
    public static boolean runUpdateName(empDAO empregadoDAO){
        try {
            Scanner read=new Scanner(System.in);
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
            return true;

        } catch (InputMismatchException errorMismatch) {
            System.out.println("ERROR typo, try again -- " + errorMismatch);
            return false;
        }
        catch (NullPointerException errorNull){
            System.out.println("Não existe nenhum usuário com esse ID -- "+errorNull);
            return false;
        }
    }
    //Runs method updateSalary with validations

    public static boolean runUpdateSalary(empDAO empregadoDAO){
        try {
            Scanner read=new Scanner(System.in);
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
            return true;
        } catch (InputMismatchException errorMismatch) {
            System.out.println("ERROR typo, try again -- " + errorMismatch);
            return false;
        }
        catch (NullPointerException errorNull){
            System.out.println("Não existe nenhum usuário com esse ID "+ errorNull);
            return false;
        }

    }
    //Runs method delete with validations

    public static boolean runDelete(empDAO empregadoDAO){
        try {
            Scanner read=new Scanner(System.in);
            boolean verifica;
            do {
                System.out.println("Digite o ID do empregado: ");
                int id = read.nextInt();
                verifica=VerifyID(id);
                if (verifica) {
                    empregadoDAO.deleteUser(id);
                }
            }while(!verifica);

            return true;
        }  catch (InputMismatchException errorMismatch) {
            System.out.println("ERROR typo, try again -- " + errorMismatch);
            return false;
        }
        catch (NullPointerException errorNull){
            System.out.println("Não existe nenhum usuário com esse ID -- "+errorNull);
            return false;
        }
    }

}
