package org.checkerframework.checker.sqli;

import com.sun.source.tree.LiteralTree;
import com.sun.source.tree.Tree;
import org.checkerframework.checker.sqli.qual.Untainted;
import org.checkerframework.common.basetype.BaseAnnotatedTypeFactory;
import org.checkerframework.common.basetype.BaseTypeChecker;
import org.checkerframework.framework.type.AnnotatedTypeMirror;
import org.checkerframework.framework.type.treeannotator.ListTreeAnnotator;
import org.checkerframework.framework.type.treeannotator.TreeAnnotator;
import org.checkerframework.javacutil.AnnotationBuilder;

import javax.lang.model.element.AnnotationMirror;

public class SqlInjectionAnnotatedTypeFactory extends BaseAnnotatedTypeFactory {

    private final AnnotationMirror UNTAINTED;

    public SqlInjectionAnnotatedTypeFactory(BaseTypeChecker checker) {
        super(checker);
        UNTAINTED = AnnotationBuilder.fromClass(elements, Untainted.class);
        this.postInit();
    }

    @Override
    protected TreeAnnotator createTreeAnnotator() {
        return new ListTreeAnnotator(
            super.createTreeAnnotator(),
            new TreeAnnotator(this) {
                @Override
                public Void visitLiteral(LiteralTree tree, AnnotatedTypeMirror type) {
                    if (tree.getKind() == Tree.Kind.STRING_LITERAL
                            || tree.getKind() == Tree.Kind.NULL_LITERAL) {
                        type.replaceAnnotation(UNTAINTED);
                    }
                    return super.visitLiteral(tree, type);
                }
            }
        );
    }
}