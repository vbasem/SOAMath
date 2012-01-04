
package utils;

import java.util.List;

public interface SimilarityComparision
{
    /**
    * compare two elemnts to find their similarity in strict manner ( upto first non matching element from offset)
    * the number returned makes sense only in comparision with 
    * other comparisions. The offest indiciates where the similarity check should beging (index of first element)
    * hence when the first elemnt matches with offset 0 a score of 1 is given and so on.
    * example : compareFromOffset("123.321", "12.431")
    * returns : 2
    */
    public int compareFromOffset(String firstElement, String secondElement, int offset);
    
    public String compareFromOffset(String targetElement, List<String> itemsComparedAgainst, int offset);
}
