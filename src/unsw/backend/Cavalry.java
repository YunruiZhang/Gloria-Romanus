package unsw.backend;

import org.graalvm.compiler.lir.aarch64.AArch64Move.Move;

public class Cavalry extends Unit {
    final static int full_cap = 500;
    public Cavalry(String name, Province location, String type) {
        super(full_cap, name, location, type);
    }
    public Move(Province dest){
        
    }
}
