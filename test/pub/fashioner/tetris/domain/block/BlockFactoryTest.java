package pub.fashioner.tetris.domain.block;


import org.junit.jupiter.api.Test;

class BlockFactoryTest {

    @Test
    void createBlock() {
        int types = BlockFactory.getTypes();
        for(int i = 1; i <= types; i++) {
            AbstractBlock block = BlockFactory.createBlock(i);
            assert block != null;
            for(int j = 0; j < 4; j++) {
                int[][] area = block.getArea();
                printBlock(area);
                int[][] rotArea = block.rotate();
                block.setArea(rotArea);
            }
        }
    }

    void printBlock(int[][] area) {
        for(int i = 0; i < area.length; i++) {
            for(int j = 0; j < area[0].length; j++) {
                System.out.print(area[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}