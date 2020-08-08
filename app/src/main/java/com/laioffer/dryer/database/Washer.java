package com.laioffer.dryer.database;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

public class Washer {
    public String name;
    public String id;
    public Washer(String name, String id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Washer that = (Washer) obj;
        return Objects.equals(name, that.name) && Objects.equals(id, that.id);
    }

    @NonNull
    @Override
    public String toString() {
        return "Washer : " + name;
    }
}
