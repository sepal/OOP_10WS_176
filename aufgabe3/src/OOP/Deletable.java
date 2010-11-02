package OOP;

public interface Deletable {
	public boolean hasBeenDeleted();
	/*
	 * (post-condition) Server object should delete itself, i.e., by deleting its content,
	 * deleting any local references to itself, marking its deletion with the hasBeenDeleted() field [which we sort of forgot to put to
	 * efficient use; meaning polling it before executing a method in a deleted object]
	 * and calling the ConsistencyManager to delete all references to this object (if necessary to
	 * remain consistent)
	 */
	public void delete();
	/*
	 * (precondition) ref has to in the process of deletion. Meaning this method should
	 * best be called by the ConsistencyManager, or the object itself, to make sure that
	 * it is not being used anymore, since its invalid.
	 * (postcondition) Any reference to object ref stored by the Server itself should be
	 * removed.
	 */
	public void deleteLocalReferencesTo(Deletable ref);
	/*
	 * BAD:
	 * This method is pretty weird/unnecessary. Its mostly (like this interface and the whole feature)
	 * a product of a night without sleep and only programming and has been programmed in very unhealthy
	 * mental state.
	 * 
	 * In retrospect it should/could be used to delete, for example, a product from a warehouse.
	 * To make sure that this product is not used in any Order associated with this warehouse, these
	 * orders would also have to be requested to delete the reference.
	 * 
	 * So a delete() method to delete the object itself, a deleteLocalReferencesTo method so the
	 * ConsistencyManager can ensure that all references to a delete object are gone and a
	 * deleteAllReferencesTo method, to ensure that an object itself does not use any "locally"
	 * invalid references.
	 * 
	 * But the implementation is more random for this last method.
	 * 
	 * Fix:
	 * (precondition) The object ref is a valid object, that should be deleted from the local context
	 * of the Server-Object (as opposed to a deletion in the whole program).
	 * (postcondition) The locally stored object ref, as well as any local references to ref  
	 * in the Server-Object have been deleted, as well as in any references to the local object ref in any
	 * close related objects.
	 * 
	 * Implementation for Warehouse could be, deleting a Product from Warehouse (NOT from the ProductFactory, 
	 * and thus from OTHER warehouses), then deleting the references to that Product in any Order associated
	 * with this warehouse.
	 */
	public void deleteAllReferencesTo(Deletable ref);
}
