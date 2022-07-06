import React, { useState, useEffect } from "react";
import '../css/Buttons.css';
import Swal from "sweetalert2";

export default function ProductUpdateForm(props) {
    const initialFormData = Object.freeze({
    productID: props.product.productID,
    productName: props.product.productName,
    productDescription: props.product.productDescription,
    productSize: props.product.productSize,
    productPrice: props.product.productPrice,
    productQuantity: props.product.productQuantity,
	productImage: props.product.productImage,
	productDiscount: props.product.productDiscount,
	productPriceWithDiscount: props.productPriceWithDiscount,
	productStatusID: props.product.productStatusID,
	productCategoryID: props.product.productCategoryID,
	productManufacturerID: props.product.productManufacturerID
  });

  const [statuses, setStatuses] = useState([]);
  function getStatuses() {
    const url = "http://localhost:8085/productStatuses";
    fetch(url, {
      method: "GET",
    })
      .then((response) => response.json())
      .then((statusesFromServer) => {
        setStatuses(statusesFromServer);
      })
      .catch((error) => {
        console.log(error);
      });
  }
  useEffect(() => {
    getStatuses();
  }, []);

  const [categories, setCategories] = useState([]);
  function getCategories() {
    const url = "http://localhost:8085/productCategories";
    fetch(url, {
      method: "GET",
    })
      .then((response) => response.json())
      .then((categoriesFromServer) => {
        setCategories(categoriesFromServer);
      })
      .catch((error) => {
        console.log(error);
      });
  }
  useEffect(() => {
    getCategories();
  }, []);

  const [manufacturers, setManufacturers] = useState([]);
  function getManufacturers() {
    const url = "http://localhost:8085/productManufacturers";
    fetch(url, {
      method: "GET",
    })
      .then((response) => response.json())
      .then((manufacturersFromServer) => {
        setManufacturers(manufacturersFromServer);
      })
      .catch((error) => {
        console.log(error);
      });
  }
  useEffect(() => {
    getManufacturers();
  }, []);

  const [formData, setFormData] = useState(initialFormData);
  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const productToUpdate = {
        productID: props.product.productID,
        productName: formData.productName,
        productDescription: formData.productDescription,
        productSize: formData.productSize,
        productPrice: formData.productPrice,
        productQuantity: formData.productQuantity,
        productImage: formData.productImage,
        productDiscount: formData.productDiscount,
        productPriceWithDiscount: formData.productPriceWithDiscount,
        productStatusID:formData.productStatusID,
        productCategoryID:formData.productCategoryID,
        productManufacturerID: formData.productManufacturerID
    };

    if (formData.productName === "") {
        Swal.fire({
        icon: 'warning',
        text: 'Product name is required field and must be filled!'
        });
       return false;
    }

    else if(formData.productPrice === "") {    
        Swal.fire({
        icon: 'warning',
        text: 'Product price is required field and must be filled!'
        });
        return false;
    }

    else if(formData.productPrice <= 0) {    
        Swal.fire({
        icon: 'warning',
        text: 'Product price must be greater than 0!'
        });
        return false;
     }

   else if(formData.productQuantity === "") {
     Swal.fire({
         icon: 'warning',
         text: 'Product quantity is required field and must be filled!'
     });
     return false;
 }

 else if(formData.productDiscount === false) {
     Swal.fire({
         icon: 'warning',
         text: 'Product discount is required field and must be filled!',
     });
     return false;
 }

 else if(formData.productStatusID === "") {
     Swal.fire({
         icon: 'warning',
         text: 'Product status is required field and must be filled!'
     });
     return false;
 }

 else if(formData.productCategoryID === "") {
    Swal.fire({
        icon: 'warning',
        text: 'Product category is required field and must be filled!'
    });
    return false;
}

else if(formData.productManufacturerID === "") {
    Swal.fire({
        icon: 'warning',
        text: 'Product manufacturer is required field and must be filled!'
    });
    return false;
}

    const url = "http://localhost:8085/product" + "/" + props.product.productID;
    const token = localStorage.getItem("token");
    const bearerToken = "Bearer " + token;

    fetch(url, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: bearerToken
      },
      product: JSON.stringify(productToUpdate),
    })
      .then((responseFromServer) => {
        console.log(responseFromServer);
      })
      .catch((error) => {
        console.log(error);
      });

    props.onProductUpdated(productToUpdate);
  };

  return (
    <div>
         <div className="reg">
           <h3>Modifying existing product</h3>
           <br/>
           <input
             value={formData.productID}
             name="productID"
             type="text"
             placeholder="productID"
             className="productID"
             onChange={handleChange}
             disabled
           />
           <br/>
           <input
             value={formData.productName}
             name="productName"
             type="text"
             placeholder="productName"
             className="productName1"
             onChange={handleChange}
           />
           <br/>
           <input
             value={formData.productDescription}
             name="productDescription"
             type="text"
             placeholder="productDescription"
             className="productDescription"
             onChange={handleChange}
           />
           <br/>
             <input
             value={formData.productSize}
             name="productSize"
             type="text"
             placeholder="productSize"
             className="productSize"
             onChange={handleChange}
           />
           <br/>
              <input
             value={formData.productQuantity}
             name="productQuantity"
             type="number"
             placeholder="productQuantity"
             className="productQuantity"
             onChange={handleChange}
           />
           <br/>
            <input
             value={formData.productPrice}
             name="productPrice"
             type="number"
             placeholder="productPrice"
             className="productPrice"
             onChange={handleChange}
           />
           <br/>
              <input
             value={formData.productImage}
             name="productImage"
             type="text"
             placeholder="productImage"
             className="productImage"
             onChange={handleChange}
           />
           <br/>
           <br/>
              <p>Product Discount:</p><input
             value={formData.productDiscount}
             name="productDiscount"
             type="checkbox"
             placeholder="productDiscount"
             className="productDiscount"
             onChange={handleChange}
           />
           <br/>
              <input
             value={formData.productPriceWithDiscount}
             name="productPriceWithDiscount"
              type="number"
             placeholder="productPriceWithDiscount"
             className="productPriceWithDiscount"
             onChange={handleChange}
           />
           <br/>
           <select name="productStatusID" onChange={handleChange} className="productStatus">
             <option value="">Choose status: </option>
             {statuses.map((status) => (
               <option key={status.productStatusID} value={status.productStatusID}>
                 {status.productStatusName}
               </option>
             ))}
             
           </select>
           <select name="productCategoryID" onChange={handleChange} className="productCategory">
             <option value="">Choose category: </option>
             {categories.map((category) => (
               <option key={category.productCategoryID} value={category.productCategoryID}>
                 {category.productCategoryName}
               </option>
             ))}
           </select>
           <select name="productManufacturerID" onChange={handleChange} className="productManufacturer">
             <option value="">Choose manufacturer: </option>
             {manufacturers.map((manufacturer) => (
               <option key={manufacturer.productManufacturerID} value={manufacturer.productManufacturerID}>
                 {manufacturer.manufacturerName}
               </option>
             ))}
           </select>
           <br/>
           <br/>
           <button onClick={() => props.onProductUpdated(null)} className="cancel"> Cancel</button>
           <button onClick={handleSubmit} className="modifyBtn">Modify</button>
         </div>
       </div>
    )
}