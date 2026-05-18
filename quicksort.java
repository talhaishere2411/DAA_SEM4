import java.util.*;

class qsort{
    int arr[];
    int n;
    qsort(){
        System.out.print("Enter the size of the array:");
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n];
        System.out.println("Enter the elements of the array:");
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }
        this.quick(arr,0,n-1);
        sc.close();

    }

    void quick(int arr[], int l, int h){
        if(l<h){
            int p = partition(arr,l,h);
            quick(arr,l,p-1);
            quick(arr,p+1,h);
        } 
    }

    int partition(int arr[], int low, int high){
        int pivot = arr[low];
        int i = low+1;
        int j = high;
        while(i<=j){
            while(i<=high && arr[i]<=pivot) i++;
            while(arr[j]>pivot) j--;
            if(i<j){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[low];
        arr[low] = arr[j];
        arr[j] =  temp;
        return j;
    }
    
    void display(){
        System.out.println("Quick sort result:");
        for(int i=0; i<n; i++){
            System.out.print(arr[i]+" ");
        }
    }
}

class quicksort{
    public static void main(String args[]){
        qsort q = new qsort();
        q.display();
    }
}
