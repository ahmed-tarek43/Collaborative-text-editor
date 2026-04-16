package model;

import crdt.block.Block;
import crdt.block.BlockCRDT;
import java.util.List;
import operations.DeleteBlockOperation;
import operations.DeleteCharacterOperation;
import operations.InsertBlockOperation;
import operations.InsertCharacterOperation;

public class Document {

    private final BlockCRDT blockCRDT;

    public Document() {
        blockCRDT = new BlockCRDT();
    }

    public void apply(InsertBlockOperation myOperation) {
        blockCRDT.insert(
                myOperation.getUserId(),
                myOperation.getClock(),
                myOperation.getBlockId(),
                myOperation.getAfterBlockId()
        );
    }

    public void apply(DeleteBlockOperation myOperation) {
        blockCRDT.delete(myOperation.getBlockId());
    }

    public void apply(InsertCharacterOperation myOperation) {
        Block currBlock = blockCRDT.getBlockById(myOperation.getBlockId());

        if (currBlock != null && !currBlock.isDeleted()) {
            currBlock.getCharCRDT().insert(
                    myOperation.getUserId(),
                    myOperation.getClock(),
                    myOperation.getValue(),
                    myOperation.getParentCharId()
            );
        }
    }

    public void apply(DeleteCharacterOperation myOperation) {
        Block currBlock = blockCRDT.getBlockById(myOperation.getBlockId());

        if (currBlock != null && !currBlock.isDeleted()) {
            currBlock.getCharCRDT().delete(myOperation.getCharId());
        }
    }

    public String getText() {
        List<Block> blocks = blockCRDT.getBlocks();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < blocks.size(); i++) {
            sb.append(blocks.get(i).getCharCRDT().getText());

            if (i < blocks.size() - 1) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }
}