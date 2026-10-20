import java.util.Scanner;
public class Hello_World {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String result = switch (sc.nextInt()) {
            case 1, 2, 3 -> "1,2,3";
            case 4 -> "4";
            case 5 -> {
                int sum = 2 + 4;
                yield sum + "";
            }
            default -> "default";
        };
        System.out.println(result);
    }
}
