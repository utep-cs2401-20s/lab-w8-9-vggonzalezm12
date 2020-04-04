
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AminoAcidLLTester{

  @Test
  public void aminoAcidCompareTest(){   // test  when HTE RNA sequences has different  4 different codons 5 times from the same aminoAcid with
                                        // another RNA sequence that has the same codons  5 times from the same AminoAcid
    // the expected outcome was 0, because even though it has different codons they all belong to the same aminoAcid char
    // the result passed since the diffrence between each codon is 0 both have 5 codons of A
    //** in this method is also being tested addCodon method  "createFromRNASequence" **
    // ** in this method is also being tested totalCounts method inside "aminoAcidCounts"**

    AminoAcidLL test = AminoAcidLL.createFromRNASequence("GCG GCU GCA GCG GCC");
    AminoAcidLL inList = AminoAcidLL.createFromRNASequence("GCA GCA GCA GCA GCA");
    int expected =  0;
    assertEquals(expected, test.codonCompare(inList));


  }


  @Test
  public void aminoAcidCompareTest2(){   // test  when to RNA sequences are have different aminoAcids chars
    // the expected outcome was -1, since the RNA sequences have the same codons and the same
    // the result passed since the diffrence between each codon is -1
    //** in this method is also being tested addCodon method  "createFromRNASequence" **
    // ** in this method is also being tested totalCounts method inside "aminoAcidCounts"**
                                                                                                     //A F K V L R
    AminoAcidLL test1 = AminoAcidLL.createFromRNASequence("GCUGCAUUUAAAACCAGAGCUAAAGCUAGAAGA");     //{4,1,2,1,0,3}; =11
    AminoAcidLL inList = AminoAcidLL.createFromRNASequence("GCAUUUAAAACCUUUGCUAAACUUGCUCUU");   //    {3,2,2,1,2,0};= 12
    int expected =  -1;                                                                          //Diff  -1
    assertEquals(expected, test1.codonCompare(inList));


  }

  @Test
  public void codonCompareTest(){   // test  when to RNA sequences are have different codons
    // the expected outcome was 7, since the RNA sequences have the same codons and the same
    // the result passed since the diffrence between each codon is 7
    //** in this method is also being tested addCodon method  "createFromRNASequence" **
    // ** in this method is also being tested totalCounts method inside "aminoAcidCounts"**
                                                                                                    // A A F K V L R
    AminoAcidLL test1 = AminoAcidLL.createFromRNASequence("GCUGCAUUUAAAACCAGAGCUAAAGCUAGAAGA");     //{3,1,1,2,1,0,3};
    AminoAcidLL inList = AminoAcidLL.createFromRNASequence("GCAUUUAAAACCUUUGCUAAACUUGCUCUU");   //    {2,1,2,2,1,2,0};
    int expected =  7;                                                                       //DiffSum 1 0 1 0 0 2 3 = 7
    assertEquals(expected, test1.codonCompare(inList));


  }


  @Test
  public void codonCompareTest2(){   // test  when to RNA sequences are the same
    // the expected outcome was 0, since the RNA sequences have the same codons and the same
    // the result passed since the diffrence between each codon is 0
    //** in this method is also being tested addCodon method  "createFromRNASequence" **
    // ** in this method is also being tested totalCounts method inside "aminoAcidCounts"**

    AminoAcidLL test1 = AminoAcidLL.createFromRNASequence("GCUGCAUUUAAAACCUUUGCUAAAGCU");
    AminoAcidLL inList = AminoAcidLL.createFromRNASequence("GCUGCAUUUAAAACCUUUGCUAAAGCU");
    int expected =  0;
    assertEquals(expected, test1.codonCompare(inList));


  }


  @Test
  public void aminoAcidListTest(){   // test if the char of aminoAcid doesnt repeat
                                      // the expected outcome was the list of amino acids A F K and V without repeating.
                                      // the result passed since both arrays matched
                                      //** in this method is also being tested addCodon method  "createFromRNASequence" **

    AminoAcidLL test1 = AminoAcidLL.createFromRNASequence("GCUGCAUUUAAAACCUUUGCUAAAGCU");
    char[] expected =  {'A','F','K','V',};
    assertArrayEquals(expected, test1.aminoAcidList());


  }

  @Test
  public void aminoAcidCountsTest(){   // test if the aminoAcidCounts method returns the array with a length of the number of codons passed.
                                         //also if the number of times that each codon appeared is correct.
                                          // the expected array is composed by 3,1,2,2,1 and the test passed since both arrays matched
                                          //** in this method is also being tested addCodon method inside "createFromRNASequence" **
                                          // ** in this method is also being tested totalCounts method inside "aminoAcidCounts"**
    AminoAcidLL test = AminoAcidLL.createFromRNASequence("GCUGCAUUUAAAACCUUUGCUAAAGCU");
    int [] expected = {3,1,2,2,1};
    assertArrayEquals(expected, test.aminoAcidCounts());


  }

  @Test
  public void aminoAcidCountsTest2(){   // test if the aminoAcidCounts method returns the array with a length of the number of codons passed.
    //also if the number of times that each codon appeared is correct.
    // also if it the method can handle lower case sensitivity
    // the expected array is composed by 3,1,2,2,1 and the test passed since both arrays matched since it not case sensitive
    //** in this method is also being tested addCodon method inside "createFromRNASequence" **
    // ** in this method is also being tested totalCounts method inside "aminoAcidCounts"**
    AminoAcidLL test = AminoAcidLL.createFromRNASequence("GCUGCaUuUAAaACCUUUGCuAAAgCU");
    int [] expected = {3,1,2,2,1};
    assertArrayEquals(expected, test.aminoAcidCounts());


  }


  @Test
  public void isSortedTest() // This method will test if the sorting in linked list considers the order of the codons even though the char is the same
  {                            // for example GCU and GCA are both AminoAcid of char A but GCA should be first than GCU
                              // the expected result is true since it only compares the char of the aminoAcid
                              //** in this method is also being tested addCodon method inside "createFromRNASequence" **
    AminoAcidLL test1 = AminoAcidLL.createFromRNASequence("GCUGCAUUUAAAACCUUUGCUAAAGCU");
    test1.isSorted();

    assertEquals(true, test1);
  }

  @Test
  public void sortTest() // This method will test if the linked list can be sorted even though if it has repeated chars
  {                     // the actual unsorted order of the list of chars is {'K','A','F','V'}
                          // The expected result after sorted is  {'A','F','K','V'} and it is expected to pass
                            //** in this method is also being tested addCodon method inside "createFromRNASequence" **
    AminoAcidLL test1 = AminoAcidLL.createFromRNASequence("AAAGCUGCAUUUACCUUUGCUAAAGCU");

    char[] expected =  {'A','F','K','V'};


    AminoAcidLL.sort(test1);

    assertEquals(expected, test1);
  }

  @Test
  public void sortTest2() // This method will test if the linked list can be sorted even though there are codons the las codon does not exist since it made up for only 2 char
  {                     // the actual unsorted order of the list of chars is {'K','A','F','V'}
    // The expected result after sorted is  {'A','F','K','V'} and it is expected to pass since the las two letters will return as null
//** in this method is also being tested addCodon method inside "createFromRNASequence" **
    AminoAcidLL test1 = AminoAcidLL.createFromRNASequence("AAAGCUGCAUUUACCUUUGCUAAAGCUCU");

    char[] expected =  {'A','F','K','V'};


    AminoAcidLL.sort(test1);

    assertEquals(expected, test1);
  }
}