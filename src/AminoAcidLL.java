class AminoAcidLL{
  private char aminoAcid;
  private String[] codons;
  private int[] counts;
  private AminoAcidLL next;

  AminoAcidLL(){

  }


  /********************************************************************************************/
  /* Creates a new node, with a given amino acid/codon 
   * pair and increments the codon counter for that codon.
   * NOTE: Does not check for repeats!! */
  public AminoAcidLL(String inCodon){
    aminoAcid = AminoAcidResources.getAminoAcidFromCodon(inCodon);// call method from aminoacid resources ( get amino acid from codon)
    codons = AminoAcidResources.getCodonListForAminoAcid(aminoAcid);
    counts = new int [codons.length];  // change to incrCounts(inCodon) *create this helper method
    next = null;
  }

  /********************************************************************************************/
  /* Recursive method that increments the count for a specific codon:
   * If it should be at this node, increments it and stops, 
   * if not passes the task to the next node. 
   * If there is no next node, add a new node to the list that would contain the codon. 
   */
  private void addCodon(String c){

    if (aminoAcid == AminoAcidResources.getAminoAcidFromCodon(c)) {

      incrementCounts(c); // increment counts to existing node of amino acid

    }
    else if(next != null) {
      next.addCodon(c); // check the next node
    }
    else {
      next = new AminoAcidLL(c); // creat a new node
      addCodon(c); // recursive call to increment the counts on this new node
    }


  }

// Helper method to increment the counts
  private void incrementCounts(String c){
    for(int i = 0; i< counts.length; i++){
      if(codons[i] == c) {
        if(counts[i] == 0){
          counts[i]=1;
        }
        else {
          counts[i]++;
        }
      }
    }
  }
  /********************************************************************************************/
  /* Shortcut to find the total number of instances of this amino acid */
  private int totalCount(){
    int sum = 0;
    for(int i = 0; i < counts.length-1; i++)
      sum = sum + counts[i];
    return sum;
  }

  /********************************************************************************************/
  /* helper method for finding the list difference on two matching nodes
  *  must be matching, but this is not tracked */
  private int totalDiff(AminoAcidLL inList){
    return Math.abs(totalCount() - inList.totalCount());

  }


  /********************************************************************************************/
  /* helper method for finding the list difference on two matching nodes
  *  must be matching, but this is not tracked */
  private int codonDiff(AminoAcidLL inList){
    int diff = 0;
    for(int i=0; i<codons.length; i++){
      diff += Math.abs(counts[i] - inList.counts[i]);
    }
    return diff;
  }

  /********************************************************************************************/
  /* Recursive method that finds the differences in **Amino Acid** counts. 
   * the list *must* be sorted to use this method */
  public int aminoAcidCompare(AminoAcidLL inList){
    int sum = totalDiff(inList);

    if (next == null) {
      return sum;
    }

    else {
      sum = sum + next.aminoAcidCompare(inList.next);

    }
    return sum;
  }

  /********************************************************************************************/
  /* Same ad above, but counts the codon usage differences
   * Must be sorted. */
  public int codonCompare(AminoAcidLL inList){
    int difference = codonDiff(inList);
    if (next == null){
      return difference;
    }
    else {
      difference = difference + next.codonCompare(inList.next);
    }
    return difference;
  }


  /********************************************************************************************/
  /* Recursively returns the total list of amino acids in the order that they are in in the linked list. */
  public char[] aminoAcidList(){
    if (next == null) {
    return new char[] {aminoAcid};
    }
    else{

      char[]a = next.aminoAcidList();
      char[] newArr = new char[a.length +1];
      newArr[0] = aminoAcid;
      int index = 1;
      while (index < newArr.length) {
        newArr[index] = this.aminoAcid;
        next.aminoAcidList();
        index ++;
      }

      return newArr;
    }

  }

  /********************************************************************************************/
  /* Recursively returns the total counts of amino acids in the order that they are in in the linked list. */
  public int[] aminoAcidCounts(){
    if (next == null) {
      return new int []{totalCount()};
    }
    else{

      int[] c = next.aminoAcidCounts();
      int[] newArr = new int [c.length +1];
      int index = 0;
      while (index < newArr.length) {
        newArr[index] = this.totalCount();
        next.aminoAcidCounts();
        index --;
      }

      return newArr;
    }
  }


  /********************************************************************************************/
  /* recursively determines if a linked list is sorted or not */
  public boolean isSorted(){

    if (next == null) {
      return true;
    }
    if (this.aminoAcid < next.aminoAcid){
        next.isSorted();
    }
    return false;
  }


  /********************************************************************************************/
  /* Static method for generating a linked list from an RNA sequence */
  public static AminoAcidLL createFromRNASequence(String inSequence){
    AminoAcidLL head = null;

    int j=0;
    String[] arr = new String [inSequence.length()/3];
      for( int i = 0; i< arr.length; i++){

        arr[i]= inSequence.substring(j,j+2);
        j = j+2;
    }
      for (int i = 0; i <arr.length; i++){
        if (head == null){
          head = new AminoAcidLL(arr[i]);
        }
        else {
          head.addCodon(arr[i]);
        }
      }


    return head;
  }


  /********************************************************************************************/
  /* sorts a list by amino acid character*/
  public static AminoAcidLL sort(AminoAcidLL inList){

    if (inList == null)
      return null;
    else {
      AminoAcidLL i = inList;

      while (i != null) {
        AminoAcidLL max = i;

        for (AminoAcidLL j = i; j != null; j = j.next) {
          if (max.aminoAcid < j.aminoAcid) {
            max = j;
          }
        }

        AminoAcidLL j = i;
        while (j.next != max) {
          j = j.next;
        }

        j.next.aminoAcid = max.next.aminoAcid;
        max.next.aminoAcid = inList.aminoAcid;
        inList.aminoAcid = max.aminoAcid;

      }
    }
    return inList;
  }
}