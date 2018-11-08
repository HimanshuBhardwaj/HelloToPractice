package com.himanshu.practice.nov.nov08;

/**
 * Created by himanshubhardwaj on 08/11/18.
 */
public class HashAndEquals {
}


class A {
    int z;
    int y;

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof A)) return false;
        final A other = (A) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.z != other.z) return false;
        if (this.y != other.y) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.z;
        result = result * PRIME + this.y;
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof A;
    }


    public String toString() {
        return "";
    }


}
