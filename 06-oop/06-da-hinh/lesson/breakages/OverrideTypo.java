class Discount {
    long apply(long price) {
        return price;
    }
}


class Broken {
    @Override
    public boolean equals(Broken other) {
        return true;
    }
}
