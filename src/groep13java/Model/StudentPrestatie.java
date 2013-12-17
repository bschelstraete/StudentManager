/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.Model;

/**
 *
 * @author Jellyfish
 */
public class StudentPrestatie {
    private Integer studentID;
    private Integer indicatorID;
    private Integer score;
    
    public StudentPrestatie(Integer studID, Integer indID, Integer score)
    {
        studentID = studID;
        indicatorID = indID;
        this.score = score;
    }
    
    public Integer getStudentID()
    {
        return studentID;
    }
    public Integer getIndicatorID()
    {
        return indicatorID;
    }
    public Integer getScore()
    {
        return score;
    }
}
