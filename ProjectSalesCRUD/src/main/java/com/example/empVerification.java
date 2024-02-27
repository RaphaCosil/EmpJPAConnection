package com.example;

import jakarta.persistence.PersistenceException;
import jakarta.persistence.RollbackException;

import java.time.LocalDate;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
public class empVerification {
    //Method verifyID, verify if ID has the value between 1000 and 9999
    public static boolean VerifyID(int id){
        return id >= 1000 && id <= 9999;
    }

    //Methods for all methods from empDAO class, with validations related to the database

    //Runs method Insert from empDAO  with validations
    public static boolean runInsert(empDAO empregadoDAO
    ){
        try {
            Scanner read=new Scanner(System.in);
            int newId;
            System.out.println("Enter the employee ID: ");
            newId = read.nextInt();

             empregadoDAO.insert(newId, "Name" + newId, "MockJob", 7902, new Date(2023, 07, 7), 2000.0, 200.0, 10);

            System.out.println("Enter the employee's name: ");
            String newName = read.next();
            System.out.println("Enter the employee's job: ");
            String newJob = read.next();
            System.out.println("Enter manager ID: ");
            int newMgr = read.nextInt();
            System.out.println("Enter the year of hire: ");
            int newYear = read.nextInt();
            System.out.println("Enter the month of hire: ");
            int newMonth = read.nextInt();
            System.out.println("Enter the day of hire: ");
            Integer newDay = read.nextInt();
            System.out.println("Enter the salary amount: ");
            Double newSal = read.nextDouble();
            System.out.println("Enter the commission amount: ");
            Double newCom = read.nextDouble();
            System.out.println("Enter the department ID: ");
            Integer newDeptId = read.nextInt();

            Date newDate =new Date(newYear-1900, newMonth - 1, newDay);
            String errorMessage="[Error]";
            boolean verificacao=true;
            if(newId>9999||newId<1000){
                errorMessage+= " Invalid ID value, enter numbers between 1000-9999.";
                verificacao=false;
            }
            if(newName.length()>10){
                errorMessage+= " The name can only be 10 characters long.";
                verificacao=false;
            }
            if(newJob.length()>9){
                errorMessage+= " The job can only be 9 characters long.";
                verificacao= false;
            }
            if(newMgr>9999 || newMgr<1000){
                errorMessage+= " Invalid maneger ID value, enter numbers between 1000-9999.";
                verificacao= false;
            }
            if(newSal>9999999.99){
                errorMessage+= " The salary can only reach the limit of 9999999.99.";
                verificacao=false;

            }
            if(newCom>9999999.99){
                errorMessage+= " The commission can only reach a limit of 9999999.99.";
                verificacao=false;
            }
            if(newDeptId>99||newDeptId<10){
                errorMessage+= " The department ID can only have a value between 99-10";
                verificacao=false;
            }
            int month=LocalDate.now().getMonthValue()-1;
            Date comparDate= new Date(LocalDate.now().getYear()-1900,month , LocalDate.now().getDayOfMonth());
            if(!newDate.before(comparDate)&& !newDate.equals(comparDate) ){
                errorMessage+= " The date must be before or equal to today.";
                verificacao=false;

            }
            if(!verificacao){
                System.out.println(errorMessage);
                return verificacao;
            }

            empregadoDAO.insert(newId, newName, newJob, newMgr, newDate , newSal, newCom, newDeptId);
            return true;
        } catch (InputMismatchException errorMismatch) {
            System.out.println("[ERROR] Typo, try again -- " + errorMismatch);
            return false;
        }
        catch (RollbackException erroRoll){
            System.out.println("[ERROR] transaction error, try again or contact technical support  "+ erroRoll);
            return false;
        }
        catch (PersistenceException erroPersist){
            System.out.println("[ERROR] Database error, try again or contact technical support  "+ erroPersist);
            return false;
        }
        catch(Exception error) {
            System.out.println("[ERROR] "+ error);
            return false;
        }
    }

    //Runs method readOne from empDAO with validations
    public static boolean runReadOne(empDAO empregadoDAO){
        try {
            Scanner read=new Scanner(System.in);

            System.out.println("Enter the employee ID: ");
            int id = read.nextInt();
            empregadoDAO.getOneUser(id);
            return true;

        } catch (InputMismatchException errorMismatch) {
            System.out.println("[ERROR] Typo, try again -- " + errorMismatch);
            return false;

        } catch (NullPointerException error) {
            System.out.println("[ERROR] This user doesn't exist, type an valid ID "+ error);
            return false;
        }
        catch (RollbackException erroRoll){
            System.out.println("[ERROR] transaction error, try again or contact technical support  "+ erroRoll);
            return false;
        }
        catch (PersistenceException erroPersist){
            System.out.println("[ERROR] Database error, try again or contact technical support  "+ erroPersist);
            return false;
        }
        catch(Exception error) {
            System.out.println("[ERROR] "+ error);
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
        }catch (NullPointerException errorNull){
            System.out.println("[ERROR] null user  "+ errorNull);
            return false;

        }
        catch (RollbackException erroRoll){
            System.out.println("[ERROR] transaction error, try again or contact technical support  "+ erroRoll);
            return false;
        }
        catch (PersistenceException erroPersist){
            System.out.println("[ERROR] Database error, try again or contact technical support  "+ erroPersist);
            return false;
        }
        catch(Exception error) {
            System.out.println("[ERROR] "+ error);
            return false;
        }
    }

    //Runs method updateName with validations
    public static boolean runUpdateName(empDAO empregadoDAO){
        try {
            Scanner read=new Scanner(System.in);
            boolean verifica;
            do {
                System.out.println("Enter the employee ID: ");
                int id = read.nextInt();
                verifica=VerifyID(id);
                if(verifica){
                    System.out.println("Enter new name: ");
                    String nome = read.next();
                    empregadoDAO.updateName(id, nome);

                }
                else{
                    System.out.println("[Error] Type an ID with four digits [1000-9999]");

                }
            }while(!verifica);
            return true;

        } catch (InputMismatchException errorMismatch) {
            System.out.println("[ERROR] Typo, try again -- " + errorMismatch);
            return false;
        }
        catch (NullPointerException errorNull){
            System.out.println("[ERROR] There is no user with this ID -- "+errorNull);
            return false;
        }
        catch (RollbackException erroRoll){
            System.out.println("[ERROR] transaction error, try again or contact technical support  "+ erroRoll);
            return false;
        }
        catch (PersistenceException erroPersist){
            System.out.println("[ERROR] Database error, try again or contact technical support  "+ erroPersist);
            return false;
        }
        catch(Exception error) {
            System.out.println("[ERROR]  "+ error);
            return false;
        }
    }
    //Runs method updateSalary with validations

    public static boolean runUpdateSalary(empDAO empregadoDAO){
        try {
            Scanner read=new Scanner(System.in);
            boolean verifica;
            do {
                System.out.println("Enter the employee ID: ");
                int id = read.nextInt();
                verifica=VerifyID(id);
                if(verifica){
                    System.out.println("Enter new salary: ");
                    double salario = read.nextDouble();
                    empregadoDAO.updateSalary(id, salario);
                }
                else{
                    System.out.println("[Error] Enter a four-digit ID [1000-9999]");
                }
            }while(!verifica);
            return true;
        } catch (InputMismatchException errorMismatch) {
            System.out.println("[ERROR] Typo, try again -- " + errorMismatch);
            return false;
        }
        catch (NullPointerException errorNull){
            System.out.println("[ERROR] There is no user with this ID "+ errorNull);
            return false;
        }
        catch (RollbackException erroRoll){
            System.out.println("[ERROR] transaction error, try again or contact technical support  "+ erroRoll);
            return false;
        }
        catch (PersistenceException erroPersist){
            System.out.println("[ERROR] Database error, try again or contact technical support  "+ erroPersist);
            return false;
        }
        catch(Exception error) {
            System.out.println("[ERROR] "+ error);
            return false;
        }


    }
    //Runs method delete with validations

    public static boolean runRemoveUser(empDAO empregadoDAO){
        try {
            Scanner read=new Scanner(System.in);
            boolean verifica;
            do {
                System.out.println("Enter the employee ID: ");
                int id = read.nextInt();
                verifica=VerifyID(id);
                if (verifica) {
                    empregadoDAO.removeUser(id);
                }
            }while(!verifica);

            return true;
        }  catch (InputMismatchException errorMismatch) {
            System.out.println("[ERROR] Typo, try again -- " + errorMismatch);
            return false;
        }
        catch (NullPointerException errorNull){
            System.out.println("[ERROR] There is no user with this ID -- "+errorNull);
            return false;
        }
        catch (RollbackException erroRoll){
            System.out.println("[ERROR] transaction error, try again or contact technical support  "+ erroRoll);
            return false;
        }
        catch (PersistenceException erroPersist){
            System.out.println("[ERROR] Database error, try again or contact technical support  "+ erroPersist);
            return false;
        }
        catch(Exception error) {
            System.out.println("[ERROR] "+ error);
            return false;
        }

    }

}