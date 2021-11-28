package com.sumesh.productservice.service


import com.sumesh.productservice.dao.ProductRepository
import com.sumesh.productservice.entity.Product
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductServiceImple : IProductService {
    @Autowired
    private lateinit var productRepository: ProductRepository

    override fun addProduct(product: Product): String {
            productRepository.save(product)
            return  "product created successfully"
    }

    override fun getProductById(id: ObjectId): Any {
        return if(productRepository.existsById(id)){
             productRepository.findById(id).get()
        }
        else{
            "product does not exist"
        }
    }

    override fun deleteProductById(id: ObjectId): String {
        return if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            "product deleted"
        }
        else{
            "product not found"
        }
    }

    override fun getAllProducts(): MutableList<Product> {
        return productRepository.findAll()
    }

    override fun getAllProductsByParticularSeller(sellerId: ObjectId): MutableList<Product>? {
        return productRepository.getProductBySellerId(sellerId)
    }



    override fun updateProductById(id: ObjectId, product: Product): Any? {
        return if (productRepository.existsById(id)){
                productRepository.deleteById(id)
                productRepository.save(product)
            "Product Updated Successfully"
        }
        else{
            "product not found"
        }
    }

    override fun searchByName(name: String): MutableList<Product> {
        return productRepository.searchByName(name)
    }


}