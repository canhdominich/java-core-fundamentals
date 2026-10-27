class Discount {
    long apply(long price) {
        return price;
    }
}


class Pub {
    public long apply(long price) {
        return price;
    }
}

class Half extends Pub {
    @Override
    long apply(long price) {
        return price / 2;
    }
}
