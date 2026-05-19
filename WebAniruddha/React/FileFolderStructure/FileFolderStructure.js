// Nested File/Folder Structure
// Expand and Collapse Feature
// Add/Remove File/Folder

import { useState } from "react";
import json from "../public/data.json";

// Render list of objects
const List = ({ list, addNodeToList, deleteNodeFromList }) => {
  const [isExpanded, setIsExpanded] = useState({});
  return (
    <div className="container">
      {" "}
      {/* this container will contain my file and folder view */}
      {list.map((node) => (
        <div key={node.id}>
          {node.isFolder && (
            <span
              onClick={() => {
                setIsExpanded((prev) => ({
                  ...prev,
                  [node.name]: !prev[node.name],
                }));
              }}
            >
              {isExpanded?.[node.name] ? "- " : "+ "}
            </span>
          )}
          <span>{node.name}</span>
          {node?.isFolder && (
            <span onClick={() => addNodeToList(node.id)}>
              <img
                src="https://cdn0.iconfinder.com/data/icons/files-and-folders-19/24/folder-action-add-512.png"
                alt="icon"
                className="icon"
              />
            </span>
          )}

          <span onClick={() => deleteNodeFromList(node.id)}><img src="https://static-00.iconduck.com/assets.00/delete-icon-1864x2048-bp2i0gor.png" alt="del-icon" className="icon" /></span>

          {isExpanded?.[node.name] && node?.children && (
            <List list={node.children} addNodeToList={addNodeToList} deleteNodeFromList={deleteNodeFromList} />
          )}
        </div>
      ))}
    </div>
  );
};

export default function FileFolderStructure() {
  const [data, setData] = useState(json);

  const addNodeToList = (parentId) => {
    const name = prompt("Enter Your Name");
    const updateTree = (list) => {
      return list.map((node) => {
        if (node.id === parentId) {
          return {
            ...node,
            children: [
              ...node.children,
              { id: Date.now().toString(), name: name, isFolder: true, children: [] },
            ],
          };
        }
        if (node.children) {
          return { ...node, children: updateTree(node.children) };
        }
        return node;
      });
    };
    setData((prev) => updateTree(prev));
  };

  const deleteNodeFromList = (itemId) => {
    const updateTree = (list) => {
        return list.filter(node => node.id != itemId).map((node) => {
            if (node.children) {
                return {...node, children: updateTree(node.children)}
            }
            return node;
        })

    }
    setData(prev => updateTree(prev))
  }

  return (
    <div className="main">
      <h1>File/Folder Structure</h1>
      <List list={data} addNodeToList={addNodeToList} deleteNodeFromList={deleteNodeFromList}/>
    </div>
  );
}
