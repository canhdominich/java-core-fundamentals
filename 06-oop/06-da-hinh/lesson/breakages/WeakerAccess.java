class OpenCard {
    public int multiplier() {
        return 1;
    }
}

class GoldOpenCard extends OpenCard {
    @Override
    int multiplier() {
        return 2;
    }
}
