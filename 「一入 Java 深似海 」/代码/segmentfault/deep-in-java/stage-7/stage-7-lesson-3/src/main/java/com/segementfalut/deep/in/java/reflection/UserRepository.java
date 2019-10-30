package com.segementfalut.deep.in.java.reflection;

import java.io.Serializable;

// Spring Data - CrudRepository
@Repository
public class UserRepository implements Comparable<UserRepository>, // getGenericInterfaces[0] = ParameterizedType ->
                                                                     // ParameterizedType.rawType = Comparable
                                                                    // ParameterizedType.getActualTypeArguments()[0] = UserRepository

                                CrudRepository<User>,      // getGenericInterfaces[1] = ParameterizedType -> ParameterizedType.rawType = CrudRepository
                                                                     // ParameterizedType.rawType = CrudRepository
                                                                     // ParameterizedType.getActualTypeArguments()[0] = User

        Serializable {             // getGenericInterfaces[2] = Class -> isInterface() == true

    @Override
    public int compareTo(UserRepository o) {
        return 0;
    }
}
