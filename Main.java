import java.util.Scanner;

class NeuralNet {
    private int l;
    private int[] n;
    private double[][] w;

    public NeuralNet(int l, int[] n) {
        this.l = l;
        this.n = n;
        this.w = new double[l - 1][];
        for (int i = 0; i < l - 1; i++) {
            w[i] = new double[n[i] * n[i + 1]];
        }
    }

    public void setWeight(int layer, int node1, int node2, double weight) {
        if (layer < 1 || layer >= l || node1 < 0 || node1 >= n[layer - 1] ||
                node2 < 0 || node2 >= n[layer]) {
            System.out.println("Invalid indices.");
            return;
        }
        int index = getNodeIndex(layer, node1, node2);
        if (index != -1)
            w[layer - 1][index] = weight;
    }

    public double getWeight(int layer, int node1, int node2) {
        if (layer < 1 || layer >= l || node1 < 0 || node1 >= n[layer - 1] ||
                node2 < 0 || node2 >= n[layer]) {
            System.out.println("Invalid indices.");
            return -1;
        }
        int index = getNodeIndex(layer, node1, node2);
        if (index != -1)
            return w[layer - 1][index];
        return -1;
    }

    private int getNodeIndex(int layer, int node1, int node2) {
        int s = 0;
        for (int i = 0; i < layer - 1; i++) {
            s += n[i] * n[i + 1];
        }
        int index = s + node1 * n[layer] + node2;
        if (index >= w[layer - 1].length) {
            System.out.println("Invalid index.");
            return -1;
        }
        return index;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Layers: ");
        int l = scanner.nextInt();

        int[] n = new int[l];
        for (int i = 0; i < l; i++) {
            System.out.print("Nodes in layer " + (i + 1) + ": ");
            n[i] = scanner.nextInt();
        }

        NeuralNet net = new NeuralNet(l, n);

        for (int i = 1; i < l; i++) {
            for (int j = 0; j < n[i - 1]; j++) {
                for (int k = 0; k < n[i]; k++) {
                    System.out.print("Weight from node " + j + " in layer " + i +
                            " to node " + k + " in layer " + (i + 1) + ": ");
                    double weight = scanner.nextDouble();
                    net.setWeight(i, j, k, weight);
                }
            }
        }

        System.out.print("Layer: ");
        int layer = scanner.nextInt();
        System.out.print("Source node: ");
        int srcNode = scanner.nextInt();
        System.out.print("Destination node: ");
        int destNode = scanner.nextInt();

        double weight = net.getWeight(layer, srcNode, destNode);
        System.out.println("Weight: " + weight);

        scanner.close();
    }
}
