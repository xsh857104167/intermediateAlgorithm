package exam3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author Murphy Xu
 * @create 2021-09-01 19:21
 */
public class Q2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){

            List<Connection> conn = new ArrayList<>();
            List<String> res = new ArrayList<>();
            int n = Integer.parseInt(in.nextLine());
            for (int j = 0; j < n; j++) {
                String[] strs = in.nextLine().split(" ");
                if ("subClassOf".equals(strs[1])){
                    conn.add(new Connection(strs[2], strs[0], true));
                }else if ("instanceOf".equals(strs[1])){
                    conn.add(new Connection(strs[2], strs[0], false));
                }
            }
            String search = in.nextLine();
            for (int i = 0; i < conn.size(); i++) {
                Connection temp = conn.get(i);
                if (temp.pre.equals(search)){
                    Collections.sort(conn, (c1, c2) -> c1.pre.compareTo(c2.pre));
                    while (temp.isSubClass && temp != null){
                        temp = next(temp.post, conn);
                    }
                    if (temp == null){
                        break;
                    }else{
                        temp.isSubClass = true;
                        res.add(temp.post);
                    }
                }
            }
            Collections.sort(res);
            for (int i = 0; i < res.size(); i++) {
                if (i == res.size() - 1){
                    System.out.println(res.get(i));
                    break;
                }
                System.out.print(res.get(i) + " ");
            }
        }
    }

    public static Connection next(String post, List<Connection> conn){
        for (int i = 0; i < conn.size(); i++) {
            if (conn.get(i).pre.equals(post)){
                return conn.get(i);
            }
        }
        return null;
    }
}

class Connection {
    String pre;
    String post;
    boolean isSubClass;

    public Connection() {
    }

    public Connection(String pre, String post, boolean isSubClass) {
        this.pre = pre;
        this.post = post;
        this.isSubClass = isSubClass;
    }
}