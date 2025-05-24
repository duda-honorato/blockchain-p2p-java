public boolean addBlock(Block newBlock) {
    Block lastBlock = getLatestBlock();
    if (lastBlock.hash.equals(newBlock.previousHash) &&
        newBlock.hash.equals(newBlock.computeHash())) {
        chain.add(newBlock);
        return true;
    }
    return false;
}
