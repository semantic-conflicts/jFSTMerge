package br.ufpe.cin.mergers.util.st;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.ufpe.cin.files.st.FilesManager;
import de.ovgu.cide.fstgen.ast.st.FSTNode;
import fpfn.ASTBranchesResult;
import fpfn.Difference;

/**
 * Encapsulates pertinent information of the merging process. A context
 * is also necessary to handle specific conflicts that simple
 * superimposition of trees is not able to address. 
 * @author Guilherme
 */
public class MergeContextSt {
	public File base;
	public File right;
	public File left;
	String outputFilePath;
	
	String baseContent = "";
	String leftContent = "";
	String rightContent= "";
	
	public List<FSTNode> addedLeftNodes = new ArrayList<FSTNode>();
	public List<FSTNode> addedRightNodes= new ArrayList<FSTNode>();
	
	public List<FSTNode> deletedBaseNodes = new ArrayList<FSTNode>();
	public List<FSTNode> nodesDeletedByLeft = new ArrayList<FSTNode>(); 
	public List<FSTNode> nodesDeletedByRight= new ArrayList<FSTNode>();

	public List<FSTNode> editedLeftNodes = new ArrayList<FSTNode>(); 
	public List<FSTNode> editedRightNodes= new ArrayList<FSTNode>();
	

	public FSTNode leftTree;
	public FSTNode baseTree;
	public FSTNode rightTree;
	public FSTNode superImposedTree;
	public String structuredOutput;
	public String unstructuredOutput;
	
	//statistics
	public long structuredMergeTime 			= 0;
	public long unstructuredMergeTime 			= 0;
	public int structuredNumberOfConflicts 		= 0;
	public int unstructuredNumberOfConflicts   	= 0;
	public int structuredMergeConflictsLOC 		= 0;
	public int unstructuredMergeConflictsLOC  	= 0;
	public int equalConflicts     				= 0;
	
	
	//FPFN
	public List<Difference> differences = new ArrayList();
	public ASTBranchesResult branches = new ASTBranchesResult();

	public MergeContextSt(){
	}

	public MergeContextSt(File left, File base, File right, String outputFilePath) {
		this.left = left;
		this.base = base;
		this.right= right;
		this.outputFilePath = outputFilePath;
		
		this.leftContent = FilesManager.readFileContent(this.left);
		this.baseContent = FilesManager.readFileContent(this.base);
		this.rightContent= FilesManager.readFileContent(this.right);
	}

	/**
	 * Joins the information of another context.
	 * @param otherContext the context to be joined with
	 */
	public MergeContextSt join(MergeContextSt otherContext){
		this.addedLeftNodes. addAll(otherContext.addedLeftNodes);
		this.addedRightNodes.addAll(otherContext.addedRightNodes);
		
		this.editedLeftNodes. addAll(otherContext.editedLeftNodes);
		this.editedRightNodes.addAll(otherContext.editedRightNodes);
		
		this.deletedBaseNodes. addAll(otherContext.deletedBaseNodes);
		this.nodesDeletedByLeft. addAll(otherContext.nodesDeletedByLeft);
		this.nodesDeletedByRight. addAll(otherContext.nodesDeletedByRight);

		this.leftTree = otherContext.leftTree;
		this.baseTree = otherContext.baseTree;
		this.rightTree = otherContext.rightTree;
		this.superImposedTree = otherContext.superImposedTree;
		
		return this;
	}
	
	public File getBase() {
		return base;
	}

	public void setBase(File base) {
		this.base = base;
	}

	public File getRight() {
		return right;
	}

	public void setRight(File right) {
		this.right = right;
	}

	public File getLeft() {
		return left;
	}

	public void setLeft(File left) {
		this.left = left;
	}

	public String getBaseContent() {
		return baseContent;
	}

	public void setBaseContent(String baseContent) {
		this.baseContent = baseContent;
	}

	public String getLeftContent() {
		return leftContent;
	}

	public void setLeftContent(String leftContent) {
		this.leftContent = leftContent;
	}

	public String getRightContent() {
		return rightContent;
	}

	public void setRightContent(String rightContent) {
		this.rightContent = rightContent;
	}
}