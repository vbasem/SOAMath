package utils.networking;

import java.util.List;

import utils.SimilarityComparision;

public class IpSimilarityComparision implements SimilarityComparision
{
    public int compareFromOffset(String firstElement, String secondElement, int offset)
    {
        return calculateConsecutiveMatchesFromOffset(firstElement, secondElement, offset);
    }

    public String compareFromOffset(String comparedAgainst, List<String> candidates, int offset)
    {
        String bestMatch = null;
        int currentTopScore = 0;

        for(String ip: candidates)
        {
            int calculatedScore = compareFromOffset(comparedAgainst, ip, offset);
            if (currentTopScore < calculatedScore)
            {
                currentTopScore = calculatedScore;
                bestMatch = ip;
            }
        }

        return bestMatch;
    }

    protected int calculateConsecutiveMatchesFromOffset(String firstElement, String secondElement, int offset)
    {
        int matchScore = 0;
        int currentOffset = offset;

        while (isNotEndOfArguments(firstElement, secondElement, currentOffset))
        {
            if (areEqual(firstElement.charAt(currentOffset), secondElement.charAt(currentOffset)))
            {
                matchScore++;
                currentOffset++;
            }
            else
            {
                break;
            }
        }

        return matchScore;

    }

    protected boolean isNotEndOfArguments(String firstArg, String secondArg, int currentPositin)
    {
        if (firstArg.length() > currentPositin && secondArg.length() > currentPositin)
        {
            return true;
        }

        return false;
    }

    protected boolean areEqual(char a , char b)
    {
        if (a == b)
        {
            return true;
        }

        return false;
    }
}
