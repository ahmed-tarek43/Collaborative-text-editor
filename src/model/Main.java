package model;

import operations.*;

public class Main {
    public static void main(String[] args) {
        Document doc = new Document();

        doc.apply(new InsertBlockOperation("block-1", null, 1, 0));
        doc.apply(new InsertCharacterOperation(1, 0, 'A', null, "block-1"));

        doc.apply(new DeleteCharacterOperation(1, 0, "block-1"));
        doc.apply(new DeleteCharacterOperation(1, 0, "block-1"));
        doc.apply(new DeleteCharacterOperation(9, 9, "block-1"));

        System.out.println("Visible text:");
        System.out.println(doc.getText());
    }
}