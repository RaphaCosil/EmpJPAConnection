package com.example;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

public class empDAO {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("default");
    EntityManager em = emf.createEntityManager();

    public boolean disconnectEm() {
        em.close();
        return true;
    }
    public boolean disconnectEmf() {
        emf.close();
        return true;
    }


    public boolean insert(int empno, String ename, String job, Integer mgr, Date hiredate, Double sal, Double comm, Integer deptno) {
            empData empData = new empData(empno, ename, job, mgr, hiredate, sal, comm, deptno);

            em.getTransaction().begin();
            em.persist(empData);
            em.getTransaction().commit();

            return true;

        }



    public boolean updateSalary(int empno, Double sal) {
            em.getTransaction().begin();
            empData empData2 = em.find(empData.class, empno);
            empData2.setSal(sal);
            em.getTransaction().commit();

            return true;
        }


    public boolean updateName(int empno, String ename) {
            em.getTransaction().begin();
            empData empData2 = em.find(empData.class, empno);
            empData2.setEname(ename);
            em.getTransaction().commit();

            return true;

    }

    public boolean removeUser(int empno) {
            em.getTransaction().begin();
            empData empData2 = em.find(empData.class, empno);
            em.remove(empData2);
            em.getTransaction().commit();
            return true;
    }


    public String getOneUser(int empno) {

            em.getTransaction().begin();
            empData empData2 = em.find(empData.class, empno);
            em.getTransaction().commit();

            return empData2.toString();


    }

    public List<empData> getAll() {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT emp FROM empData emp ORDER BY emp.empno");
            List<empData> empData = query.getResultList();

            return empData;

    }
}



