/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java;

/**
 *
 * @author Jellyfish
 */
public class DBaseSQL {
    private DBConnect conn;
    private String SQLStatement;
    
    public DBaseSQL()
    {
        SQLStatement = "";
    }
    
    public void voegCompetentieToe(String competentie)
    {
        SQLStatement = competentie;
    }
    public void pasCompetentieAan(String competentie)
    {
        SQLStatement = competentie;
    }
    public void verwijderCompetentie(String competentie)
    {
        SQLStatement = competentie;
    }
    
    public void koppelIndactorAanPartim(byte indicator, String partim)
    {
        SQLStatement = indicator + partim;
    }
    
    public void voerIndactorInStudent(byte indicator, String student, String partim)
    {
        SQLStatement = indicator + student + partim;
    }
    
    public void volgStudentOp(String student)
    {
        SQLStatement = "SELECT * FROM studenten WHERE student.naam IS " + student;
    }
}
