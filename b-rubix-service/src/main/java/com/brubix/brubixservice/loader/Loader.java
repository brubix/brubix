package com.brubix.brubixservice.loader;

import java.util.List;

public interface Loader<T, K> {

    void load(List<T> data);

    K mapToEntity(T t);
}
