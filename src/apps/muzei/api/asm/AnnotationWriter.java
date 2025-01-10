package apps.muzei.api.asm;

public abstract interface AnnotationWriter
{
  public abstract void a(ClassVisitor paramClassVisitor);
  
  public abstract void visitEnum(ClassVisitor paramClassVisitor);
}
