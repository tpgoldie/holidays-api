package com.tpg.holidays.model;

public interface AddressFixture {

    default Address address(String lineOne, String lineTwo, String municipal, String state, String postCode) {

        return new Address(lineOne, lineTwo, municipal, state, postCode);
    }
}
