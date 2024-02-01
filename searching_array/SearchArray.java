class SearchArray {
    
    public static boolean searchForString(String[] arr, String word){
        boolean exists = false;
        int i = 0;
        while (exists && i < arr.length) {
            exists = word.equals(arr[i]);
        }
        return exists;
        
    }
        
    public static void main(String[] args) {
        String[] arr = {"The", "Man", "Walked"};
        System.out.println(searchForString(arr, "The"));
    }

}