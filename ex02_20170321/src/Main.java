
public class Main {
	public static void main(String arg[]){
		int nArraySize = 15;
		int[] Heap = new int[nArraySize];
		System.out.print("input:\t");
		
		for(int i=0; i<nArraySize ; i++){
			Heap[i]= (int)(Math.random()*100);
			System.out.print(Heap[i]+" ");
			int child=i;
			int parent = (child==0)? -1:(child-1)/2;
			while (parent>=0){
				if(Heap[parent]>Heap[child]){
					int temp = Heap[parent];
					Heap[parent] = Heap[child];
					Heap[child] = temp;
							
					child = parent;
					parent = (child==0)? -1:(child-1)/2;
				}else{
					break;
				}
			}
		}
		
		System.out.println();
		System.out.print("heap:\t");
		for(int i:Heap){
			System.out.print(+i+" ");
		}
		System.out.println("\n----------");
		
		int j=0,k=0;
		for(int i:Heap){
			System.out.print(i+" ");
			j++;
			if(Math.pow(2, k)==j){
				j=0; k++;
				System.out.println();
			}
		}
	}
}
